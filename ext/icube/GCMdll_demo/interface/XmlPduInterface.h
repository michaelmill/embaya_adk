//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube, Christian Aymon, 1376 Goumoens-la-Ville, Switzerland
//          infos@icube.ch
//
// (C)Copyright 2004-2011
//**********************************************************************
#ifndef XmlPduInterface_h
#define XmlPduInterface_h

struct TXmlPduStatus
{
  int ErrorKind;
  char ErrorMessage[256];
  int ErrorIndex;
} ;

static const int XMLPDU_NO_ERROR = 0x0;
static const int XMLPDU_XML_ERROR = 0x1;
static const int XMLPDU_OBJECT_ERROR = 0x2;
static const int XMLPDU_PDU_ERROR = 0x3;

typedef void *TXmlPduBuffer;

#ifdef XMLPDU_EXPORTS
#define XMLPDU_API __declspec(dllexport)
#else
#define XMLPDU_API
#endif

extern "C" XMLPDU_API void  XmlToPdu(const char * pXmlRef, TXmlPduBuffer &PduBuffer, TXmlPduStatus &Status);
extern "C" XMLPDU_API void  PduToXml(const unsigned char * pPduRef, int iPduSize, TXmlPduBuffer &XmlBuffer, TXmlPduStatus
  &Status);

extern "C" XMLPDU_API void  XmlPduReleaseBuffer(TXmlPduBuffer &Buffer);
extern "C" XMLPDU_API const unsigned char *  XmlPduBufferData(TXmlPduBuffer Buffer);
extern "C" XMLPDU_API int  XmlPduBufferSize(TXmlPduBuffer Buffer);
extern "C" XMLPDU_API const char * XmlPduVersion();

#endif  // XmlPduInterface_h
