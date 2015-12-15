#!/bin/sh

DIR_NAME=`dirname $0`
cd $DIR_NAME


if [ ! -e ~/.asciidoc/themes/openmuc ];
then
    mkdir -p ~/.asciidoc/themes/
    ln -s `pwd`/themes/openmuc ~/.asciidoc/themes/openmuc
fi

asciidoc -a icons -a iconsdir=/etc/asciidoc/images/icons -a max-width=55em -a toc2 -a theme=openmuc -a toc jdlms-doc.txt
