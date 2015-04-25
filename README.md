# SHA-Bushido

Отбор "Бушидо" участващ в хакатона HackFMI 5..

# Workflow
Client Android app + Server Android app + Raspberry Pi (for unlocking)

## Client 
1. Wifi (to work with the server)
// Can connect to specific server and can turn on

2. RSA (for secure communication with the server)
// We have class that can encrypt/decrypt

3. NFC (for initial communication 2 send the identification of the client)
4. Pattern + PIN
5. FaceRecognition (Qualcom, to send the face data to the server)

#### Client Activities:

1. Connect/Main (button to open NFS and connect with progress bar)
2. Pin (activity with pin code)
3. Face Recognition (activity with photo of you)
4. Unlock (activity with buton to turn on/off the locker)

#### Server Activities
1. Main Activity
1.1 On/Off Server
1.2 On/Off NFC (20sec timeout)
1.3 Textfield with log


## Server
1. Wifi (to work with the cilent)
2. RSA (secure connection with client)
3. NFC (initial recieving of data, it could not be enabled at the programmatically)
4. Logic workflow - get the data and do some thing with it.
5. SSH to the Pi for unlocking

## Pi
1. Wifi for connecting the server client
2. Python script to unlock the locker

## Additional & Bonus
* Presentation on Sunday
* Initial setup of the applications

