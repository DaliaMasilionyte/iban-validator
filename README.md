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
You will be promptly asked to type an IBAN. Result will be a boolean answer: 
**true** if IBAN is valid;
**false** if IBAN is invalid.

## Mode "1"
You will be promptly asked to type in a path to the desired file. 
**Input** need to be structured in a following manner (One IBAN per line):
```
AA051245445454552117989
LT647044001231465456
LT517044077788877777
LT227044077788877777
CC051245445454552117989
```
Result will be an output file using the same name, but the extension will be **".out"**. 

*Example result:*
```
AA051245445454552117989;false
LT647044001231465456;true
LT517044077788877777;true
LT227044077788877777;false
CC051245445454552117989;false
```