@echo off
set source=E:\Coden\Purworld\target\paper-Purworld.jar
set destination=C:\Users\fabia\AppData\Roaming\.feather\player-server\servers\1db9a4dd-a456-45ac-8758-2bbb9b5e6ded\plugins\paper-Purworld.jar

if exist "%source%" (
    echo Datei "%source%" gefunden.
    echo Verschiebe Datei nach "%destination%".
    move /Y "%source%" "%destination%"
    echo Datei wurde erfolgreich verschoben.
) else (
    echo Datei "%source%" nicht gefunden.
)
pause
