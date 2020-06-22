#!/bin/sh

DIC_FILE=data/mg/mg_MG
OUTPUT_FILE=data/mg/mg_wordlist.txt

java -jar Hunspell2WordList.jar $DIC_FILE $OUTPUT_FILE
