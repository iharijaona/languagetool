#!/bin/sh

LANG_CODE=mg
PREFIX=${LANG_CODE}_MG
WORD_LIST=data/mg/mg_wordlist.txt
FREQ_FILE=data/mg/mg_wordlist.xml

OUTPUT_FILE=data/mg/${PREFIX}.dict
INFO_FILE=data/mg/${PREFIX}.info

LT_VERSION=4.7-SNAPSHOT
REPO=~/.m2/repository
CPATH=$REPO/com/carrotsearch/hppc/0.7.2/hppc-0.7.2.jar:$REPO/com/beust/jcommander/1.48/jcommander-1.48.jar:$REPO/org/carrot2/morfologik-fsa-builders/2.1.6/morfologik-fsa-builders-2.1.6.jar:$REPO/org/carrot2/morfologik-stemming/2.1.6/morfologik-stemming-2.1.6.jar:$REPO/org/carrot2/morfologik-fsa/2.1.6/morfologik-fsa-2.1.6.jar:$REPO/org/carrot2/morfologik-tools/2.1.6/morfologik-tools-2.1.6.jar:$REPO/commons-cli/commons-cli/1.4/commons-cli-1.4.jar:../languagetool-tools/target/languagetool-tools-${LT_VERSION}.jar
echo $CPATH


java -cp $CPATH:../languagetool-standalone/target/LanguageTool-$LT_VERSION/LanguageTool-$LT_VERSION/languagetool.jar:../languagetool-standalone/target/LanguageTool-$LT_VERSION/LanguageTool-$LT_VERSION/libs/languagetool-tools.jar \
  org.languagetool.tools.SpellDictionaryBuilder -i $WORD_LIST -info $INFO_FILE -o $OUTPUT_FILE -freq $FREQ_FILE
