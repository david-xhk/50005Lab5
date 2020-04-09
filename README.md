# 50.005 Lab 5

*Encryption Lab: **Symmetric Key Encryption and Message Digest***

## Part 1

To see the outputs for each of the questions, comment out the respective line in [`DesSolution.main`](https://github.com/han-keong/50005Lab5/blob/master/src/main/java/cse/lab5/DesSolution.java):
- `question1_PrintContent_OfInputFile_ToScreen()`
- `question2_EncryptContent_OfInputFile_AndPrintCipherBytes_ToScreen()`
- `question3_EncryptContent_OfInputFile_ThenBase64Encode_AndPrintCipherBytes_ToScreen()`
- `question5_EncryptContent_OfInputFile_ThenDecrypt_AndPrintCipherBytes_ToScreen()`
- `question6_EncryptContent_OfInputFiles_AndPrint_LengthsOfCipherBytes_ToScreen()`
 
### Question 1

*Try to print to your screen the content of the input files, i.e., the plaintexts, using ​`System.out.println()`.​*  
*What do you see? Are the files printable?*

![Output for Question 1](https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/screenshots/Screenshot%202020-04-09%20at%205.24.47%20AM.png)

- The files are indeed printable (in human-readable form).
- `shorttext.txt` is a poem while `longtext.txt` is an e-book.

### Question 2

*Store the output ciphertext (in ​`byte[]`​ format) to a variable, say `cipherBytes`​.*  
*Try to print the ciphertext of the smaller file using​ `System.out.println(new String(cipherBytes))`​.*  
*What do you see? Is it printable?*

![Output for Question 2](https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/screenshots/Screenshot%202020-04-09%20at%205.25.51%20AM.png)

- The ciphertext is not printable.
- Garbage is printed to the screen, with no human-readable information.

### Question 3

*Now convert the ciphertext in Question 2 into ​Base64​ format and print it to the screen.*  
*Is the ​Base64​ encoded data generally printable?*

![Output for Question 3](https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/screenshots/Screenshot%202020-04-09%20at%205.26.21%20AM.png)

- Yes, Base64 encoded data is generally printable.
- A long string of random characters is printed.

### Question 4

*Is ​Base64​ encoding a cryptographic operation? Why or why not?*

- Base64 is an encoding scheme that converts a stream of bits/bytes into a series of printable characters.
- It can be easily encoded and decoded using a lookup table.
- Thus, it is not a cryptographic operation.

### Question 5

*Print out the decrypted ciphertext for the small file. Is the output the same as the output for question 1?*

![Output for Question 5](https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/screenshots/Screenshot%202020-04-09%20at%205.26.40%20AM.png)

- Yes, the decrypted ciphertext gave the same output as question 1 when it was printed.

### Question 6

*Compare the lengths of the encryption result (in `​byte[]` ​format) for `shorttext.txt` and `longtext.txt`.*  
*Does a larger file give a larger encrypted byte array? Why?*

![Output for Question 6](https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/screenshots/Screenshot%202020-04-09%20at%205.27.02%20AM.png)

- `shorttext.txt` and `longtext.txt` have 1474 and 17353 characters respectively.
- The encryption results for `shorttext.txt` and `longtext.txt` are 1480 and 17360 bytes long respectively.
- Thus, the larger the file, the larger the encrypted byte array.
- A cryptography operation is not like a hashing operation. Thus, with more plaintext, more ciphertext needs to be generated.

## Part 2

### Question 1

*Compare the original image with the encrypted image. What similarities between them do you observe?*  
*Can you identify the original image from the encrypted one?*

- The edges that are present in the original image are also visible in the encrypted image.
- Because the edges are distinguishable, it is possible to identify the original image from the encrypted one.

### Question 2

*Why do those similarities exist? Explain the reason based on what you find out about how the ECB mode works.*

- ECB stands for **Electronic Code Book**.
- An ECB works by substituting blocks of plaintext with a corresponding ciphertext value, and vice versa.
- Thus, distinct patterns in the plaintext, such as edges, have a high chance of bleeding into the ciphertext.

### Question 3

*Now try to encrypt the image using the CBC mode instead (i.e., by specifying `"​DES/CBC/PKCS5Padding​"`).*  
*Compare the result with that obtained using ECB mode. What differences do you observe?*  
*Explain the differences based on what you find out about how CBC mode works.*

- The encrypted image that is generated is very different from the original image.
- The image is filled with RGB noise without any noticeable pattern.
- CBC stands for **Cipher Block Chaining**.
- A CBC works by doing an XOR operation on a block of plaintext with the previous ciphertext block before encrypting that block of plaintext. 
- In order to ensure uniqueness, a unique initialization vector is used.
- Since each block of ciphertext depends on all the previous plaintext blocks, patterns in the plaintext will no longer map directly onto the ciphertext.

### Question 4

*Do you observe any issue with image obtained from CBC mode encryption of `“​SUTD.bmp​”`?*  
*What is the reason for such observation? Can you explain and try on what would be the result if data were taken from bottom to top along the columns of the image?*  
*Can you try your new approach on​ comment on observation?*























































