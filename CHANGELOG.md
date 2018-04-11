# Back log of changes in NavigineSDK library

All notable changes to this project will be documented in this file. NavigineSDK
uses calendar versioning in the format `YYYYMMDD`.

## Version 20180411
[Reference](https://github.com/Navigine/Android-SDK/blob/d4c0e75ed5b40c266da4561f91a9f1070fd3196c/NavigineSDK/NavigineSDK.jar)

* Improved navigation algorithms

* Fixed bugs in network API

* Added validation of parameter `server_url` according to the pattern:
`http[s]://hostname[:port]`

* DeviceInfo: added function `getGlobalPoint` for converting sublocation
coordinates of the device into the global geographic coordinates:
[wiki](https://github.com/Navigine/Android-SDK/wiki/Class-DeviceInfo#function-getglobalpoint)

* Added abstract interface `DeviceInfo.Listener`:
[wiki](https://github.com/Navigine/Android-SDK/wiki/Class-DeviceInfo.Listener)

* NavigationThread: added function `setDeviceListener`. Function is called
every time when NavigationThread updates the device position.
[wiki](https://github.com/Navigine/Android-SDK/wiki/Class-NavigationThread#function-setdevicelistener)

* LocationView.Listener: functions
```java
void onScroll ( float x, float y )
void onZoom   ( float ratio )
```
are replaced by:
```java
void onScroll ( float x, float y, boolean isTouchEvent )
void onZoom   ( float ratio,      boolean isTouchEvent )
```

[wiki](https://github.com/Navigine/Android-SDK/wiki/Class-LocationView.Listener)

## Version 20180302

* NavigineSDK: function `getLocationFile` is marked as **deprecated**. In future it
will be removed.

* NavigationThread: function `loadLocation`, taking the location archive file
path as argument, is replaced by:
```java
public boolean loadLocation(int locationId)
public boolean loadLocation(String location)
```

[wiki](https://github.com/Navigine/Android-SDK/wiki/Class-NavigationThread#function-loadlocation)

## Version 20180221

* Improved navigation algorithms

* NavigationThread: added function `setTrackFile` for track recording:
[wiki](https://github.com/Navigine/Android-SDK/wiki/Class-NavigationThread#function-settrackfile)

## Version 20170601

* Support of PNG maps with large dimensions added.

* SVG maps support improved.

* Minor bugs were fixed.

## Version 20170427

* Fixed crush in location downloading.

* Navigation accuracy improved.

## Version 20170314

* Background service stability and battery usage improved.

* Navigation accuracy improved.

## Version 20170220

* Major updates in internal SDK storage - to support locations with local symbols in name.

* Navigation accuracy improved.

* Fixed bug with iBeacon battery level transmition to Navigine CMS.