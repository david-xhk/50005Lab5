# 50.005 Lab 5: Encryption Lab

## Overview

*In NS Module 3, we examined how the security properties of **confidentiality** and **data integrity** could be protected by using **symmetric key cryptography** and **signed message digests**.*

*In this lab exercise, you will learn how to write a program that makes use of **DES** for data encryption, **MD5** for creating message digests and **RSA** for digital signing.*


## Part 1: *Symmetric Key Encryption for a Text File*

*In this task, we will make use of the `Cipher` and `KeyGenerator` classes from the Java Cryptography Extension (JCE) to encrypt and decrypt data from a text file.*

*Complete the file [`DesSolution.java`][des-solution] so that it can encrypt an input text file using **DES**.*

*Use your program to encrypt the provided files ([`shorttext.txt`][short-text] and [`longtext.txt`][long-text]) and answer the following questions.*

[des-solution]: https://github.com/han-keong/50005Lab5/blob/master/src/main/java/cse/lab5/DesSolution.java
[short-text]: https://github.com/han-keong/50005Lab5/blob/master/src/main/resources/shorttext.txt
[long-text]: https://github.com/han-keong/50005Lab5/blob/master/src/main/resources/longtext.txt


### Question 1

*Try to print to your screen the content of the input files, i.e., the plaintexts, using*

```java
System.out.println(text);
```

*What do you see? Are the files printable?*

#### Answer

- The files are indeed printable (in human-readable form).

- `shorttext.txt` is a poem while `longtext.txt` is an e-book.

#### Outputs

> **[Output][q1-shorttext] for `shorttext.txt`**  
> ```
> Original text: 
> 
> I've seen the world
> Done it all
> Had my cake now
> Diamonds, brilliant
> And Bel Air now
> ...
> ```

> **[Output][q1-longtext] for `longtext.txt`**  
> ```
> Original text: 
> 
> The Project Gutenberg EBook of The Sign of the Four, by Arthur Conan Doyle
> 
> This eBook is for the use of anyone anywhere at no cost and with
> almost no restrictions whatsoever.  You may copy it, give it away or
> re-use it under the terms of the Project Gutenberg License included
> ...
> ```

- To see outputs from running code, comment out the following line in [`DesSolution.main`][des-solution].
 
```java
public static void main(String[] args)
    throws IOException
{
    question1_GetText_FromInputFiles(); // Uncomment this line
    ...
}
```

[q1-shorttext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question1_OriginalText_shorttext.txt
[q1-longtext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question1_OriginalText_longtext.txt


### Question 2

*Store the output ciphertext (in ​`byte[]`​ format) to a variable, say `cipherBytes`​.*

*Try to print the ciphertext of the smaller file using​*

```java
System.out.println(new String(cipherBytes));
```

*What do you see? Is it printable?*

#### Answer

- The ciphertext is not printable.

- Garbage is printed to the screen, with no human-readable information.

#### Outputs

> **[Output][q2-shorttext] for `shorttext.txt`**  
> ```
> Encrypted bytes as String: 
> 
> c�$>7-���O�vZ�)����Z#[�@�'^���=|f���*UZ�3_�=�����B�_ޡ��>P���<�������-9rU:����$�$�����,���l��qyAw��:�Pg�����AQ�@
> �;������W�a,��_J��&�~c~�֑����E'�J~�7�8,�n�[��M��k�go^+A���pw]�3XWy��S}��yOlh�HH��u<\
> )nh<̚�"*��.F��9Gᰥ�P�+��o���}�e>������xT�o��1d���UJd%��Mו#�
> �o��R���*8t�%���K��-�O8�u�;!�^5�c�Y��(�Җ%����W����M2=���@��H��Td�5��_2��yځ�̣���}؇B6�l�k����׎�@'�9�9\`����&�Jc,P���Rz���L~�R�t���~i��"��	��n�]OI�؇����l	4NsGt:�Bt�,�G	����t�$�)�`��:��c�px�y�R	�iw;�8VA����&���X^x�cKP[�?���}[[<�9�FCU�^�p^^�tV^6�?u�@��K�3�~Ph��+�_S������L�K�!B�cB�z���4�@��y��3l+�J:H��;���n,����Q�v��vd�{렫���F��@�����"
> A嵟��cx�xB���Kٴ�Z�T�I�5�Ϡ��h�ea���4f(���;��g�$����.b�P�Ug[~�B�>�b_$���3@�B��젇P�C{����ɳ��h��J2~�D�%2�:-�W����gY��(�����6Ϊz��5��Tn �7�0f�	�½�4�"��2G%<��<���>�E��tT>@2P��-�S��ը�!�`�n�q`*q��QTNb�	x2u
> ...
> ```

> **[Output][q2-longtext] for `longtext.txt`**  
> ```
> Encrypted bytes as String: 
> 
> qt�1��u~YV*;�5d�ϨD5�]�uvt�罆���dX���
> 1��	�@|��`\8����ľߗ��)�������<ly��c��n%7w%�Y���$�j�`���rxW� =!2�S67����]җQ��.aT�@���[q��lc+�4۩cG{�b멟�*ǐ�_��a���&O7�0D�z�ح��\@���FL���ƺ��Z�h�`��^5��,Z���Fb�אF#,��6���c��F#b��C	��z�0j�2�`�>��N*w*C������P�p1}�wY���qL�gL
> {jh4�LO��d�8xd�<�W��5�nE�U�S��3;����&����c'~�d� v�d��K�����L2
> DcJE鴧p+ק	��v�SYrM�0��M�b[-�iǂ�R��>�_����=������,_�	X3*qڏ?*�k��d��'6ȫ�uk�[}V�!�%�X����"���_CXe���@ޡ���=�y�M=iW�d1gbĆ7\�"A��%���u1�mIm~�R
> .S_�����
> ...
> ```

- To see outputs from running code, comment out the following line in [`DesSolution.main`][des-solution].

```java
public static void main(String[] args)
    throws IOException
{
    ...
    question2_EncryptText_FromInputFiles_ThenConvert_EncryptedBytes_ToString(); // Uncomment this line
    ...
}
```

[q2-shorttext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question2_EncryptedBytes_AsString_shorttext.txt
[q2-longtext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question2_EncryptedBytes_AsString_longtext.txt


### Question 3

*Now convert the ciphertext in Question 2 into ​Base64​ format and print it to the screen.*

*Is the ​Base64-encoded data generally printable?*

#### Answer

- Yes, Base64-encoded data is generally printable.

- A long string of random characters and symbols is printed.

#### Outputs

> **[Output][q3-shorttext] for `shorttext.txt`**  
> ```
> Encrypted bytes in Base64 format: 
> 
> xFpkfavQfa/sSerZ57ogoJB5/GWSnEfQkXtMWxCksq2ZNnnWyGCxGuppgTsR7hYr7YHNRMG54RM+gp/L/Q9HcFcg4QmkNo1iY/4838RQ9Ii+AeSmcxMBuIOHGv5XjhtlWYig+LFSyI2w7zhKQz8Tw17DP+t9qQJW3MgTQqXXy1BV0on7nUM1NOzbYx/RYyGMHtbUy5PMPD9fIdXAssqby0uUYMthZItjwVyaaDm6Zxw3+Gm1X+sChuxm1hSXCQIG+n15UhSlHt2yeQ7/q44/EtdXYD1h2bAOvkXxbJ7f8lL35liFuMt4vYMMDCZNf8yh4Qseq0wQsoaxphT6Kt8yD++j/SHmhXDgs3Z+9twj2Qfn5jYCm4G8oQRHs1pE3KqnK91UtpjJd0k528a9M2KP5WhTZhscX8SDsehD3mgB/u9gGd1B6X4I7FcAsAbsN39Z0k0waUosutiVGJA1W9z3yI+/BK8/Qy7WPk0XpVyaZRp41S5MfrmIafFVeHfOCQ+F4V7I+LFQvmVs+u2KMzhDcwC7V/9KMAcuLaskACCBIcYBATzc8rf+m/G+e2S7RJl0KBuMVrLMmSZ3pR52Nn02doKdmaYMPOKgnRYcCplSAmw0h0xrRopBP2jUeJxctJexOqwXDkLOxuMDA1t/XFPMARyGcMOett0MgcRp4GCRJzZtmV8u3dsQTcOCMklAIAI5s0ti/liFVwLgR4qhPzxQWM5Z/7PZGHWKKyV4spKso8VKJVtLtIvhsGJxPnoN7LgC1qPujADdoGOmZu2ySVgLfqZkiR4lUA1OyP+OrLn0gGZW19S1rADkeCZ6eIof3yjmBW4S10hlfb17NGg2xdfTYKDkQ8pfOKc9ZQS7Eb+s1TlBr2JWOrKSLTQhYY5X8PddGhhi1fIZ0IJeoCOBR2aojmyzYryzIZ5w6sQwT/iSl/G3h14kOyXPfIGuSg0x0oFgbIHn5A7xa0lhPHUHdv+oFIgh2Uv1nay8r/tF354yGt1aoOk7weB+d7XiQfTAoAEM8/IKujfzhBZM27thUT4b8VWBYCNCOEkL2+VGYFs619dM8Gw9sfuu2gOw3bZLgYHVdRa1qxA/SUlSc96Eo6e3/5UYkDVb3PfIHpoIGjRpsbObEg0X/ySStUDji6Z9F7WCEFY1vWsofz8rzb3ApoXdcTAr5u58QVsfZRJBiPZa/JTXEywcgZgLp/AEasuNmgJqCnsluKfuXnrPgyZAMV6n+3SjDCnR41ccwFKh5Yv4EVcyOnTiO0Cm591qazbb05QegS6ElNkILt3PrIbcMeWilGyZWMfwaJO+WtgMvmj6Fb03zA9/06zueXZFXmKk7EY4vw005xROuEdeDFDa7oJr2LUhsk3cHzvKpo6cyOlRBbDiZVB3qGNqIGM2cjvpXjiIqDP8svghGzeN6QZeWkIGD1lE1mY9iupsgZm2jmYNjBsyw5fsZZUvO4P2W60V3o2AwM0Wnj/32hnMyaXmQOfUQepyt7Baf2aecPSy4Y8X9JcER7NaRNyqpyvdVLaYyXdJOdvGvTNij+VrpVG2ApatgwpdyJwSydjpYxzTUTorWyZE/0mSNwqQxYxndqphOtlyP5OeCSoQ/f7AYj9tGNmW2D2T5swr+3H45PVRUEJfQp2gL7agt/5uU45RF8vzC6Q6otdnREkCsWo8s6yej6RrMLDeqh6vZjoFyawlGZfbvkqVGJA1W9z3yD6W7PQLALC5qeg0wfKQcYjENon/7ypaMzQhYY5X8PddGhhi1fIZ0IKyZcH0emPDFGyzYryzIZ5w6sQwT/iSl/Gzdn723CPZB+fmNgKbgbyhBEezWkTcqqcr3VS2mMl3SWqrhotCsoGAa6VRtgKWrYPmlIol6xUIX+7LSuGX80lELuY3lXSfqmSsbh9jTlnYnEYaim5MGgh+VHk6jNRxvCup62QMayXEv0KzrGgYX7qTGgP+mltG6uM8Om342pts0w==
> ```

> **[Output][q3-longtext] for `longtext.txt`**  
> ```
> Encrypted bytes in Base64 format: 
> 
> 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
> ```

- To see outputs from running code, comment out the following line in [`DesSolution.main`][des-solution].

```java
public static void main(String[] args)
    throws IOException
{
    ...
    question3_EncryptText_FromInputFiles_ThenConvert_EncryptedBytes_ToBase64Format(); // Uncomment this line
    ...
}
```

[q3-shorttext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question3_EncryptedBytes_InBase64Format_shorttext.txt
[q3-longtext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question3_EncryptedBytes_InBase64Format_longtext.txt


### Question 4

*Is ​Base64​ encoding a cryptographic operation? Why or why not?*

#### Answer

- Base64 is an encoding scheme that converts a stream of bits/bytes into a series of printable characters.

- It can be easily encoded and decoded using a lookup table.

- Thus, it is not a cryptographic operation.


### Question 5

*Print out the decrypted ciphertext for the small file. Is the output the same as the output for Question 1?*

#### Answer

- Yes, the decrypted ciphertext gave the same output as the first question when it was printed.

#### Outputs

> **[Output][q5-shorttext] for `shorttext.txt`**  
> ```
> Decrypted bytes as String: 
> 
> I've seen the world
> Done it all
> Had my cake now
> Diamonds, brilliant
> And Bel Air now
> ...
> ```

> **[Output][q5-longtext] for `longtext.txt`**  
> ```
> Decrypted bytes as String: 
> 
> The Project Gutenberg EBook of The Sign of the Four, by Arthur Conan Doyle
> 
> This eBook is for the use of anyone anywhere at no cost and with
> almost no restrictions whatsoever.  You may copy it, give it away or
> re-use it under the terms of the Project Gutenberg License included
> ...
> ```

- To see outputs from running code, comment out the following line in [`DesSolution.main`][des-solution].

```java
public static void main(String[] args)
    throws IOException
{
    ...
    question5_EncryptText_FromInputFiles_ThenDecrypt_AndConvert_DecryptedBytes_ToString(); // Uncomment this line
    ...
}
```

[q5-shorttext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question5_DecryptedBytes_AsString_shorttext.txt
[q5-longtext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question5_DecryptedBytes_AsString_longtext.txt


### Question 6

*Compare the lengths of the encryption result (in `​byte[]` ​format) for `shorttext.txt` and `longtext.txt`.*

*Does a larger file give a larger encrypted byte array? Why?*

#### Answer

- `shorttext.txt` and `longtext.txt` have 1474 and 17353 characters respectively.

- After encryption, `shorttext.txt` and `longtext.txt` have 1480 and 17360 bytes respectively.

- Thus, the larger the file, the larger the encrypted byte array.

- A cryptography operation is not like a hashing operation. Thus, with more plaintext, more ciphertext needs to be generated.

- Some extra bytes are added as padding to the last block of plaintext to achieve the fixed size required by DES.

- This is because DES is a block cipher, which requires inputs to be in blocks with fixed sizes.

#### Outputs

> **[Output][q6-shorttext] for `shorttext.txt`**  
> ```
> shorttext.txt text length: 1474
> shorttext.txt number of bytes: 1474
> shorttext.txt number of encrypted bytes: 1480
> ```

> **[Output][q6-longtext] for `longtext.txt`**  
> ```
> longtext.txt text length: 17353
> longtext.txt number of bytes: 17353
> longtext.txt number of encrypted bytes: 17360
> ```

- To see outputs from running code, comment out the following line in [`DesSolution.main`][des-solution].

```java
public static void main(String[] args)
    throws IOException
{
    ...
    question6_EncryptText_FromInputFiles_ThenGetLengthsOf_EncryptedBytes(); // Uncomment this line
}
```

[q6-shorttext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question6_NumberOfBytes_AndEncryptedBytes_shorttext.txt
[q6-longtext]: https://github.com/han-keong/50005Lab5/blob/master/src/main/results/part1/Question6_NumberOfBytes_AndEncryptedBytes_longtext.txt


## Part 2: *Symmetric Key Encryption for an Image File*

*In this task, we will use **DES** to encrypt an image file and vary the cipher mode used to observe any effects on the encryption.*

*Complete the file [`​DesImageSolution.java`][des-image-solution] ​to encrypt the input image files using **DES** in **ECB** mode.*

*Use your program to encrypt the provided files ([`SUTD.bmp`][sutd-bmp] and [`triangle.bmp`][triangle-bmp]) and answer the following questions.*

[des-image-solution]: https://github.com/han-keong/50005Lab5/blob/master/src/main/java/cse/lab5/DesImageSolution.java
[sutd-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/SUTD.bmp
[triangle-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/triangle.bmp


### Question 1

*Compare the original image with the encrypted image. What similarities between them do you observe?*

*Can you identify the original image from the encrypted one?*

#### Answer

- The edges that are present in the original image are also visible in the encrypted image.

- Because the edges are distinguishable, it is possible to identify the original image from the encrypted one.

#### Outputs

> **[Original][sutd-bmp] `SUTD.bmp`**  
> ![Original image for SUTD.bmp][sutd-bmp]
> 
> **[Output][q1-sutd-bmp] for `SUTD.bmp`**  
> ![DES-ECB encrypted image for SUTD.bmp][q1-sutd-bmp]

> **[Original][triangle-bmp] `triangle.bmp`**  
> ![Original image for triangle.bmp][triangle-bmp]
> 
> **[Output][q1-triangle-bmp] for `triangle.bmp`**  
> ![DES-ECB Encryption of triangle.bmp][q1-triangle-bmp]

- To see outputs from running code, comment out the following line in [`DesImageSolution.main`][des-image-solution].

```java
public static void main(String[] args)
    throws IOException
{
    question1_EncryptImage_FromInputFiles_UsingDES_WithECB(); // Uncomment this line
    ...
}
```

[q1-sutd-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question1_ECB_SUTD.bmp
[q1-triangle-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question1_ECB_triangle.bmp


### Question 2

*Why do those similarities exist? Explain the reason based on what you find out about how the ECB mode works.*

#### Answer

- ECB stands for **Electronic Code Book**.

- An ECB works by substituting blocks of plaintext with a corresponding ciphertext value, and vice versa.

- Thus, distinct patterns in the plaintext, such as edges, have a high chance of bleeding into the ciphertext.


### Question 3

*Now try to encrypt the image using **CBC** mode instead (i.e., by specifying `"​DES/CBC/PKCS5Padding​"`).*

*Compare the result with that obtained using ECB mode. What differences do you observe?*

*Explain the differences based on what you find out about how CBC mode works.*

#### Answer

- The encrypted image that is generated is very different from the original image.

- The image is filled with RGB noise without any noticeable pattern.

- CBC stands for **Cipher Block Chaining**.

- A CBC works by doing an **XOR operation** on a block of plaintext with the previous ciphertext block before encrypting that block of plaintext.

- For the XOR operation on the very first block of plaintext, a **unique random initialization vector** is used to ensure that the cipher will be secure.

- Since each block of ciphertext depends on all the previous plaintext blocks, patterns in the plaintext will no longer map directly onto the ciphertext.

#### Outputs

> **[Original][sutd-bmp] `SUTD.bmp`**  
> ![Original image for SUTD.bmp][sutd-bmp]
> 
> **[Output][q3-sutd-bmp] for `SUTD.bmp`**  
> ![DES-CBC encrypted image for SUTD.bmp][q3-sutd-bmp]

> **[Original][triangle-bmp] `triangle.bmp`**  
> ![Original image for triangle.bmp][triangle-bmp]
> 
> **[Output][q3-triangle-bmp] for `triangle.bmp`**  
> ![DES-CBC Encryption of triangle.bmp][q3-triangle-bmp]

- To see outputs from running code, comment out the following line in [`DesImageSolution.main`][des-image-solution].

```java
public static void main(String[] args)
    throws IOException
{
    ...
    question3_EncryptImage_FromInputFiles_UsingDES_WithCBC(); // Uncomment this line
    ...
}
```

[q3-sutd-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question3_CBC_SUTD.bmp
[q3-triangle-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question3_CBC_triangle.bmp


### Question 4

*Do you observe any issue with image obtained from CBC mode encryption of `“​SUTD.bmp​”`?*

*What is the reason for such observation? Can you explain and try on what would be the result if data were taken from bottom to top along the columns of the image?*

*Can you try your new approach on​ comment on observation?*

#### Answer

- Yes, there is a silhouette of the original image that is formed by random RGB strips that run across the encrypted image.

- This gives away information about the original image, although the silhouette may be filled with random RGB noise.

  - For example, the [triangle][q3-triangle-bmp] can still visibly be seen in the output for `triangle.bmp`.

- This issue probably stems from how each plaintext block is XOR-ed with the previous ciphertext block.

- When the plaintext has many repeated sections of bytes, the ciphertext generated will become regular.

- A clear example of this is [`square.bmp`][square-bmp] (See below).

  - In this example, no RGB noise can be seen at all. Only the silhoutte of the square and the 'shadow' that it casts can be seen.

  - Since the square is a solid red and the background is a solid green, the entire image only consists of repeated sections of bytes.
  
  - Thus, the generated ciphertext is highly regular and no RGB noise is effectively generated.

- This phenomenon can still be observed when the images are encrypted in a bottom-up manner.

  - The silhouettes are still visible, but now the 'shadow' is casted from the bottom side towards the top of the image.
  
  - Since repeated sections are still repeated when read in the opposite direction, the same problem may still arise.
  
  - However, a repeated section of bytes could be masked by the 'shadow' of a previous non-repeating section.
  
  	- This can be observed in the [output][q4-sutd-bmp] of `SUTD.bmp` (See below).
  	
  	- More RGB noise is generated when the image is encrypted bottom up.
  	
  	- This is because the bottom side of the image has many non-repeating sections of bytes across the image that form the phrase "*Established in collaboration with MIT*".
  	
  	- Thus, the repeating sections of white pixels at the top of the image now become masked as the ciphertext has been scrambled previously.

#### Outputs

> **[Original][square-bmp] `square.bmp`**  
> ![Original image for square.bmp][square-bmp]
> 
> **[Output][q4-square-bmp] for `square.bmp`**  
> ![DES-CBC encrypted image for square.bmp][q4-square-bmp]

> **[Original][sutd-bmp] `SUTD.bmp`**  
> ![Original image for SUTD.bmp][sutd-bmp]
> 
> **[Output][q4-sutd-bmp] for `SUTD.bmp`**  
> ![DES-CBC encrypted image for SUTD.bmp][q4-sutd-bmp]

> **[Original][triangle-bmp] `triangle.bmp`**  
> ![Original image for triangle.bmp][triangle-bmp]
> 
> **[Output][q4-triangle-bmp] for `triangle.bmp`**  
> ![DES-CBC Encryption of triangle.bmp][q4-triangle-bmp]

- To see outputs from running code, comment out the following line in [`DesImageSolution.main`][des-image-solution].

```java
public static void main(String[] args)
    throws IOException
{
    ...
    question4_EncryptImage_FromInputFiles_BottomUp_UsingDES_WithCBC(); // Uncomment this line
}
```

[square-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/resources/square.bmp
[q4-square-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question4_CBC_square.bmp
[q4-sutd-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question4_CBC_BottomUp_SUTD.bmp
[q4-triangle-bmp]: https://raw.githubusercontent.com/han-keong/50005Lab5/master/src/main/results/part2/Question4_CBC_BottomUp_triangle.bmp


## Part 3: *Signed Message Digests*

*In NS Module 3, we learned how a **signed message digest** could be used to guarantee the **integrity** of a message.*

*Signing the **digest** instead of the message itself gives much better efficiency.*

*In the final task, we will use JCE to create and sign a message digest*

*Complete the file​ [`DigitalSignatureSolution.java`][digital-signature-solution] ​so that it can generate a signed message digest of an input file.*

*Apply your program to the provided text files ([`shorttext.txt`][short-text] and [`longtext.txt`][long-text]) and answer the following questions.*

[digital-signature-solution]: https://github.com/han-keong/50005Lab5/blob/master/src/main/java/cse/lab5/DigitalSignatureSolution.java


### Question 1

*What are the sizes of the message digests that you created for the two different files? Are they the same or different?*

#### Answer

### Question 2

*Compare the sizes of the signed message digests in the following line of code for `shorttext.txt` and `longtext.txt`.*

```java
​byte[] encryptedBytes = eCipher.doFinal(data.getBytes());
```

*Does a larger file size give a longer signed message digest? Why or why not?*

#### Answer

