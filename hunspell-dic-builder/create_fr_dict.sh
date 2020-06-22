#!/bin/sh

echo "Call from LT source top directory"

mvn package -DskipTests

LANG_CODE=fr
PREFIX=${LANG_CODE}_FR
# this is the list of words as exported from https://github.com/Fanaen/Hunspell2WordList:
WORD_LIST=/home/dnaber/lt/fr.out.uniq
# Get this from https://github.com/mozilla-b2g/gaia/tree/master/apps/keyboard/js/imes/latin/dictionaries:
FREQ_FILE=/home/dnaber/lt/occurrence_counts/fr_wordlist.xml

CONTENT_DIR=languagetool-language-modules/${LANG_CODE}/src/main/resources/org/languagetool/resource/$LANG_CODE/hunspell

LT_VERSION=4.0-SNAPSHOT
REPO=~/.m2/repository
CPATH=$REPO/com/carrotsearch/hppc/0.7.1/hppc-0.7.1.jar:$REPO/com/beust/jcommander/1.48/jcommander-1.48.jar:$REPO/org/carrot2/morfologik-fsa-builders/2.1.2/morfologik-fsa-builders-2.1.2.jar:$REPO/org/carrot2/morfologik-stemming/2.1.2/morfologik-stemming-2.1.2.jar:$REPO/org/carrot2/morfologik-fsa/2.1.2/morfologik-fsa-2.1.2.jar:$REPO/org/carrot2/morfologik-tools/2.1.2/morfologik-tools-2.1.2.jar:$REPO/commons-cli/commons-cli/1.2/commons-cli-1.2.jar:languagetool-tools/target/languagetool-tools-${LT_VERSION}.jar
echo $CPATH

OUTPUT_FILE=/tmp/fr_FR.dict
INFO_FILE=${CONTENT_DIR}/${PREFIX}.info

java -cp $CPATH:languagetool-standalone/target/LanguageTool-$LT_VERSION/LanguageTool-$LT_VERSION/languagetool.jar:languagetool-standalone/target/LanguageTool-$LT_VERSION/LanguageTool-$LT_VERSION/libs/languagetool-tools.jar \
  org.languagetool.tools.SpellDictionaryBuilder -i $WORD_LIST -info $INFO_FILE -o $OUTPUT_FILE -freq $FREQ_FILE
