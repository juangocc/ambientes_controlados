/*************************************************************
* EEPROM data memory interface for PIC16 and PIC18 devices 
*
* Allows for read and write of the EEPROM data memory. 
*
* Warning! Interrupts are disabled during
*          and restored after write!
*
* THIS SOFTWARE IS PROVIDED IN AN "AS IS" CONDITION. 
* NO WARRANTIES, WHETHER EXPRESS, IMPLIED OR STATUTORY, 
* INCLUDING, BUT NOT LIMITED TO, IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
* APPLY TO THIS SOFTWARE. SOURCEBOOST TECHNOLOGIES SHALL 
* NOT, IN ANY CIRCUMSTANCES, BE LIABLE FOR SPECIAL, 
* INCIDENTAL OR CONSEQUENTIAL DAMAGES, FOR ANY REASON 
* WHATSOEVER.
*
* (C) 2005-2006, L. Hollevoet, BoostC compiler.
*
* Adapted to use in SourceBoost installation by Pavel Baranov
* (C) 2006-2010, Pavel Baranov, David Hobday
*************************************************************/

#ifndef _EEPROM_H_
#define _EEPROM_H_

#ifdef _PIC16
	unsigned char eeprom_read( unsigned char address );
	void eeprom_write( unsigned char address, unsigned char data );
#elif _PIC16x
	unsigned char eeprom_read( unsigned char address );
	void eeprom_write( unsigned char address, unsigned char data );
#elif _PIC18
	unsigned char eeprom_read( unsigned char address );
	void eeprom_write( unsigned char address, unsigned char data );
	#ifdef EEADRH
	unsigned char eeprom_read( unsigned short address );
	void eeprom_write( unsigned short address, unsigned char data );
	#endif
#else
	#error "unsupported target"
#endif

#endif // _EEPROM_H_
