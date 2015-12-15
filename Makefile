all:
	rm lib.a
	rm e
	g++ -Wall -c lib.cpp
	ar -cvq lib.a lib.o
	g++ -Wall -c main.cpp \
                     ext-gurux-clean/GXDLMSVariant.cpp \
                     ext-gurux-clean/GXDateTime.cpp \
                     ext-gurux-clean/GXOBISTemplate.cpp 
	g++ -o e main.o \
                     GXDLMSVariant.o \
                     GXDateTime.o \
                     GXOBISTemplate.o \
                     lib.a
