#include "WiFi.h"
#include <HTTPClient.h>
#include <Arduino_JSON.h>
const char* ssid = "ERYK_GDZIE_JESTES";
const char* password =  "ujck1234";

const char* serverNameStatus = "http://192.168.148.68:8080/status_sensor";
const char* serverNamePOSTSensors = "http://192.168.148.68:8080/add_sensors";

String apiStatus[1];
String statusJSON;
double lastTime = 0;

bool ledIsOn = false;

int testingPin = 2;
int temperaturePin = 33;
int waterLevelPin = 32;
int lightnessPin = 34;
int vibrationsPin = 35;

String wLSensorName = "Water level";
String tSensorName = "Temperature";
String lSensorName = "Lightness";
String vSensorName = "Vibrations";

int wLSensorId = 2;
int tSensorId = 3;
int lSensorId = 4;
int vSensorId = 5;

String waterLevelUnit = "[%] PERCENT";
String temperatureUnit = "[C] CELSIUS";
String lightnessUnit = "[L] LUX";
String vibrationUnit = "[A] AMPLITUDE";

void setup() {

  Serial.begin(115200);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }

  Serial.println("Connected to the WiFi network");

  pinMode(testingPin, OUTPUT) ;
  digitalWrite(testingPin, HIGH);

}

void loop() {

  if ((millis() - lastTime) > 200) {
    //Check WiFi connection status
    if (WiFi.status() == WL_CONNECTED) {

      WiFiClient client;
      HTTPClient http;

      http.begin(client, serverNamePOSTSensors);

      http.addHeader("Content-Type", "application/json");
      String httpRequestDataWaterLevel = "{\"name\":\"" + wLSensorName + "\",\"value\":\"" + String(analogRead(waterLevelPin)) 
      + "\",\"id\":\"" + wLSensorId + "\",\"unit\":\""+ waterLevelUnit + "\"}";
      String httpRequestDataTemperature = "{\"name\":\"" + tSensorName + "\",\"value\":\"" + String(((analogRead(temperaturePin) * 5.0) / 1024.0) * 100 / 4) 
      + "\",\"id\":\"" + tSensorId + "\",\"unit\":\""+ temperatureUnit + "\"}";
      String httpRequestDataLightness = "{\"name\":\"" + lSensorName + "\",\"value\":\"" + String(analogRead(lightnessPin)) + "\",\"id\":\"" 
      + lSensorId + "\",\"unit\":\""+ lightnessUnit +"\"}";

      String httpRequestArray = "[" + httpRequestDataWaterLevel + "," + httpRequestDataTemperature + "," + httpRequestDataLightness + "]";
      int httpResponseCodeTemperature = http.POST(httpRequestArray);
      Serial.println(httpRequestArray);

      statusJSON = httpGETRequest(serverNameStatus);
      Serial.println(statusJSON);
      JSONVar myObject = JSON.parse(statusJSON);

      // JSON.typeof(jsonVar) can be used to get the type of the var
      if (JSON.typeof(myObject) == "undefined") {
        Serial.println("Parsing input failed!");
        return;
      }

      Serial.print("JSON object = ");
      Serial.println(myObject);

      // myObject.keys() can be used to get an array of all the keys in the object
      JSONVar keys = myObject.keys();

      for (int i = 0; i < keys.length(); i++) {
        JSONVar value = myObject[keys[i]];
        Serial.print(keys[i]);
        Serial.print(" = ");
        Serial.println(value);
        apiStatus[i] = value;
      }
      Serial.print("1 = ");
      Serial.println(apiStatus[0]);
    }
    else {
      Serial.println("WiFi Disconnected");
    }
    //////////////////////////////////////////////////////
    if (!ledIsOn) {
      ledIsOn = true;
      digitalWrite(testingPin, HIGH);
    }
    else if (ledIsOn) {
      ledIsOn = false;
      digitalWrite(testingPin, LOW);
      Serial.print("Temperature: ");
      Serial.println(((analogRead(temperaturePin) * 5.0) / 1024.0) * 100 / 4);
      Serial.print("Water Level: ");
      Serial.println(analogRead(waterLevelPin));
      Serial.print("Lightness: ");
      Serial.println(analogRead(lightnessPin));
      Serial.print("Vibrations: ");
      Serial.println(analogRead(vibrationsPin));
    }
    //////////////////////////////////////////////////////////

    lastTime = millis();
  }
}

String httpGETRequest(const char* serverName) {
  WiFiClient client;
  HTTPClient http;

  // Your Domain name with URL path or IP address with path
  http.begin(client, serverName);

  // Send HTTP POST request
  int httpResponseCode = http.GET();

  String payload = "{}";

  if (httpResponseCode > 0) {
    Serial.print("HTTP Response code: ");
    Serial.println(httpResponseCode);
    payload = http.getString();
  }
  else {
    Serial.print("Error code: ");
    Serial.println(httpResponseCode);
  }
  // Free resources
  http.end();

  return payload;

}
