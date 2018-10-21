# IBAN validator

### 1. Introduction
A program that validates the given IBAN by checking its length, symbol validity and check digits.
It was created using information provided at [Wikipedia](https://en.wikipedia.org/wiki/International_Bank_Account_Number).

The program works in two modes - interactive and validating from file.

### 2. Running
Project is already compiled.
Using command line as you are in the root folder type:
```bash
./run.sh
```
You will be asked to select a mode.

**0** - interactive.

**1** - reading from a file.

## Mode "0"
You will be promptly asked to type an IBAN. 

Result will be a boolean answer: 

**true** if IBAN is valid;

**false** if IBAN is invalid.

## Mode "1"
You will be promptly asked to type in a path to the desired file. 
**Input** need to be structured in a following manner (One IBAN per line):
```
LT556754077788877777
AL35202111090000000001234567
LT601010012345678901
HR1723600001101234565
AB0238787733388822234485995444339
```
Result will be an output file using the same name, but the extension will be **".out"**. 

*Example result:*
```
LT556754077788877777;false
AL35202111090000000001234567;true
LT601010012345678901;true
HR1723600001101234565;true
AB0238787733388822234485995444339;false
```