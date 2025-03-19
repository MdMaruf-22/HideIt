# HideIt

This project is a simple steganography tool that allows users to hide and retrieve messages in images using Java's Swing framework.

## Features
- Select an image (JPG, JPEG, PNG, BMP, GIF)
- Encode a secret message into an image
- Decode and retrieve the hidden message from an encoded image
- Simple GUI with buttons for interaction

## Installation & Usage

### Prerequisites
- Java 8 or later

### How to Run
1. Clone this repository or download the files.
   ```sh
   git clone https://github.com/MdMaruf-22/HideIt.git
   cd HideIt
   ```
2. Compile the Java files.
   ```sh
   javac -d . src/org/example/*.java
   ```
3. Run the application.
   ```sh
   java org.example.Main
   ```

## Usage Instructions

### Selecting an Image
1. Click the **Select Image** button.
2. Choose an image file from your system.
3. The selected image will be displayed, and the **Encode** and **Decode** buttons will be enabled.

### Encoding a Message
1. Click the **Encode** button.
2. Enter the secret message to hide.
3. The message will be embedded into the image, and a new encoded image will be saved in the same directory as the original file.

### Decoding a Message
1. Click the **Decode** button.
2. If the image contains an encoded message, it will be displayed.

### Help Screen
Click the **Help** button to view usage instructions.

## Project Structure
```
HideIt/
│── src/
│   ├── org/example/Main.java          # GUI and event handling
│   ├── org/example/SteganographyApp.java # Encoding & decoding logic
│── README.md                          # Project documentation
```

## How Steganography Works in This Project
- The message length (32 bits) is stored in the first few pixels.
- The message characters are stored in the least significant bits of subsequent pixels.
- Decoding extracts the message length first, then reconstructs the message bit by bit.

## Example Output
```
Message encoded successfully in the image: encoded_image.png
Decoded message: Hello, Steganography!
```


