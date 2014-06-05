#ifndef _Config_h
#define _Config_h

#include <string.h>
//#include "eeprom.h"

#pragma CLOCK_FREQ 20000000
#pragma DATA _CONFIG, _PWRTE_ON & _BODEN_OFF & _WDT_OFF & _LVP_OFF & _CPD_OFF & _DEBUG_OFF & _HS_OSC & _CP_OFF


unsigned short sw_SPBRG@0x47;	// define location for the emulated SSPCON1
unsigned short sw_RCREG@0x48;	// define location for the emulated SSPCON2
unsigned short sw_TXREG@0x49;	// define location for the emulated SSPSTAT
unsigned short sw_TXSTA@0x4A;	// define location for the emulated SSPBUF
unsigned short sw_RCSTA@0x4B;	// define location for the emulated SSPADD
unsigned short sw_TXIF_PIR@0x4C;// define location for the emulated TXIF_PIR1
unsigned short sw_RCIF_PIR@0x4C;// define location for the emulated RCIF_PIR1

// Sample PIC16F87x defaults for software emulated USART support
#define TX_PORT		0x07
#define TX_TRIS		0x87
#define TX_BIT		6
#define RX_PORT		0x07
#define RX_TRIS		0x87
#define RX_BIT		7
#define e_SPBRG		0x99
#define e_RCREG		0x1a
#define e_TXREG		0x019
#define e_TXSTA		0x98
#define e_RCSTA		0x18
#define e_TXIF_PIR	0x0c
#define e_RCIF_PIR	0x0c
#define e_TXIF_BIT	4
#define e_RCIF_BIT	5
#define MODE		(USART_reset_wdt | USART_HW)

//#define bit_time 86	// 115200 baud at 40MHz
#define bit_time 520	// 9600 baud at 20MHz
	
#include <rs232_driver.h>



//0(LSB),1,2,..n(MSB)
//pines para calefactores. 4
#define pin_C1_0 portb.0 
#define pin_C1_1 portb.1
#define pin_C2_0 portb.2
#define pin_C2_1 portb.3	


//pines para extractores 8
#define pin_E1_0 portd.4
#define pin_E1_1 portd.5
#define pin_E1_2 portd.6
#define pin_E1_3 portd.7


#define pin_E2_0 portd.0
#define pin_E2_1 portd.1
#define pin_E2_2 portd.2
#define pin_E2_3 portd.3

//pines para Aire Acondicionado 4
#define pin_AA_0 portb.4 //
#define pin_AA_1 portb.5
#define pin_AA_2 portb.6
#define pin_AA_3 portb.7

//pines para persiana	2
#define pin_per_0 portc.4
#define pin_per_1 portc.5

//pines para bombillos	7
#define port_Bomb porta 
#define port_Bomb6 porte.0
#define port_Bomb7 porte.1

//pin para puerta	1
#define pin_puerta porte.2

//pin ind recepcion de trama
#define pin_indRecTrama portc.2

//pines para sensor
#define DATA_SHT 	portc.0		//señal de datos
#define SCK_SHT 	portc.1		//señal de reloj
#define TRIS_DATA 	trisc.0
#define TRIS_SCK 	trisc.1


/**********TRIS*************/
//0(LSB),1,2,..n(MSB)
//pines para calefactores. 4
#define tris_C1_0 trisb.0
#define tris_C1_1 trisb.1
#define tris_C2_0 trisb.2
#define tris_C2_1 trisb.3	



//pines para extractores 8
#define tris_E1_0 trisd.4
#define tris_E1_1 trisd.5
#define tris_E1_2 trisd.6
#define tris_E1_3 trisd.7

#define tris_E2_0 trisd.0
#define tris_E2_1 trisd.1 	
#define tris_E2_2 trisd.2
#define tris_E2_3 trisd.3

//pines para Aire Acondicionado 4
#define tris_AA_0 trisb.4
#define tris_AA_1 trisb.5
#define tris_AA_2 trisb.6
#define tris_AA_3 trisb.7

//pines para persiana
#define tris_per_0 trisc.4
#define tris_per_1 trisc.5

//pines para bombillos
#define tris_port_Bomb trisa 
#define tris_port_Bomb6 trise.0
#define tris_port_Bomb7 trise.1

//pin para puerta
#define tris_puerta trise.2

//pin para indicar recepcion de trama
#define tris_indRecTrama trisc.2
 
 




char calef1, calef2;  	//C1, C2 y C3
char Extract1, Extract2;  //E1, E2 y E3	
char AA;  		//A1
char Persiana; 	//P1
char Temp; 		//ST
char Hum; 		//SH
bit puerta;  	//X1			Abierta o cerrada. 
char bombillos; 
char RegEstado; 
char ESTADO; 
char UltValor; 
char LongTrama;
char Origen; 
char Destino; 
char nom_val=0; 


//variables temporales. 
char temp; 
char nom_temp, nom_temp_h; 
char val_temp; 
//ESTADOS

#define INICIO		0
#define CABECERA_P 	1
#define CABECERA_A 	2
#define LONGITUD 	3
#define ORIGEN		4
#define DESTINO		5
#define vESTADO		6
#define DISPOSITIVO	7
#define FIN_CAB_M	8
#define FIN_CAB_R	9
#define DirHABITACION 102
/**
VALORES EN DECIMAL
PC		100
T.C.M	101
HAB1	102
HAB2	103
HAB3	104
TS H1	105
TS H2	106
TS H3	107
*/


#define C1_EEPROM 0x03
#define C2_EEPROM 0x04
#define AA_EEPROM 0x05
#define E1_EEPROM 0x06
#define E2_EEPROM 0x07
#define BX_EEPROM 0x08
#define P1_EEPROM 0x09

void SetDispositivo(char n1, char n2, char val)
{

	//puts("ingresa a set Dispositivo");
	//if(()&&()&&())
	if((n1=='C')&&(n2=='1'))
	{
			calef1 = val; 
			eeprom_write(C1_EEPROM,val);
			pir2.EEIF = 0;
			//puts("SE ESTABLECIO C1 \n");
	}
	
	if((n1=='C')&&(n2=='2'))
	{
			calef2 = val; 
			eeprom_write(C2_EEPROM,val);
			pir2.EEIF = 0;
			//puts("SE ESTABLECIO C2 \n");
	}
	
		
	if((n1=='E')&&(n2=='1'))
	{
			Extract1 = val;
			eeprom_write(E1_EEPROM,val); 
			pir2.EEIF = 0;
			//puts("SE ESTABLECIO E1 \n");
	}
	
	if((n1=='E')&&(n2=='2'))
	{
			Extract2 = val;
			eeprom_write(E2_EEPROM,val); 
			pir2.EEIF = 0;
			//puts("SE ESTABLECIO E2 \n");
	}
	
	if((n1=='A')&&(n2=='A'))
	{
			AA = val; 
			eeprom_write(AA_EEPROM,val);
			pir2.EEIF = 0;
			//puts("SE ESTABLECIO AA \n");
	}
	
	if((n1=='P')&&(n2=='1'))
	{
			Persiana = val; 
			eeprom_write(P1_EEPROM,val);
			pir2.EEIF = 0;
			
	}
	
	if((n1=='B')&&(n2=='X'))
	{
			bombillos = val;  
			eeprom_write(BX_EEPROM,val);
			pir2.EEIF = 0;
			//puts("SE ESTABLECIO BX \n");
			
	}
		
}

void setPuertos()
{
//puts("setPuertos"); 

pin_C1_0 = calef1.0; 
pin_C1_1 = calef1.1;

pin_C2_0 = calef2.0; 
pin_C2_1 = calef2.1;

pin_E1_0 = Extract1.0; 
pin_E1_1 = Extract1.1;
pin_E1_2 = Extract1.2; 
pin_E1_3 = Extract1.3;

pin_E2_0 = Extract2.0; 
pin_E2_1 = Extract2.1;
pin_E2_2 = Extract2.2; 
pin_E2_3 = Extract2.3;


if(AA == 1) // Encender / apagar --> se baja la temperatura. 
{

	pin_AA_3  = 1; 
	delay_ms(700); 
	pin_AA_3 = 0; 
	
	delay_s(2); 
	
	pin_AA_2  = 1; 
	delay_ms(700); 
	pin_AA_2 = 0; 
	
	delay_s(2); 
	
	pin_AA_2  = 1; 
	delay_ms(700); 
	pin_AA_2 = 0; 


}else if(AA == 2)
{
	pin_AA_1  = 1; 
	delay_ms(700); 
	pin_AA_1 = 0; 
	
	delay_s(1); 
}else if(AA == 3)
{
	pin_AA_0  = 1; 
	delay_ms(700); 
	pin_AA_0 = 0; 
	
	delay_s(1); 
}


pin_per_0 = Persiana.0;
pin_per_1 = Persiana.1;


port_Bomb.0 = bombillos.0; 
port_Bomb.1 = bombillos.1; 
port_Bomb.2 = bombillos.2; 
port_Bomb.3 = bombillos.3; 
port_Bomb.4 = bombillos.4; 
port_Bomb.5 = bombillos.5; 
port_Bomb6 = bombillos.6; 
port_Bomb7 = bombillos.7; 
}

void ResetPuertos()
{
pin_C1_0 = 0; 
pin_C1_1 = 0;
pin_C2_0 = 0; 
pin_C2_1 = 0;

pin_E1_0 = 0;
pin_E1_1 = 0;
pin_E1_2 = 0;
pin_E1_3 = 0;

pin_E2_0 = 0;
pin_E2_1 = 0;
pin_E2_2 = 0;
pin_E2_3 = 0;

pin_AA_0 = 0;
pin_AA_1 = 0;
pin_AA_2 = 0;
pin_AA_3 = 0; 


pin_per_0 = 0;
pin_per_1 = 0;
port_Bomb = 0x00;
port_Bomb6 = 0; 
port_Bomb7 = 0; 

}

void limpiarVariables()
{
	calef1 = 0; 
	
	calef2 = 0; 
		
	Extract1 =  0; 
	
	Extract2 = 0; 
	
	AA = 0; 
	
	Persiana = 0; 
	
	bombillos = 0; 
	
}

void ResponderTrama(char Destino)
{
	
	char *trama = "PALODEMR";
		//longitud
		trama[2] = 0; 
		//Dir Origen
		trama[3] = DirHABITACION; 
		//Dir Destino
		trama[4] = Destino; 
		//Estado
		trama[5] = 2; 
		puts_(trama,8); 
		
}

void ResponderEEPROM(char Destino)
{
	
	char *trama = "PALODEXXXXXXXXXXMR";
		//longitud
		trama[2] = 10; 
		//Dir Origen
		trama[3] = DirHABITACION; 
		//Dir Destino
		trama[4] = Destino; 
		//Estado
		trama[5] = 2; 
		
		//Calefactor1
		trama[6] = eeprom_read(C1_EEPROM);
		//Calefactor2 
		trama[7] = eeprom_read(C2_EEPROM);
		//Extractor 1 
		trama[8] = eeprom_read(E1_EEPROM);
		//Extractor 2 
		trama[9] = eeprom_read(E2_EEPROM);
		 //
		trama[10] = 0; 
		//Persianas
		trama[11] = eeprom_read(P1_EEPROM); 
		//Iluminacion 	
		trama[12] = eeprom_read(BX_EEPROM);
		 
		trama[13] = 0; 
		trama[14] = 0; 
		trama[15] = 0; 
		
		puts_(trama,18); 
		
}


#endif 