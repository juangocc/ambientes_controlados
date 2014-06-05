#include <system.h>
#include "eeprom.h"
#include "Config.h"

	
char *p1=""; 
short i = 0; 
char tam; 
bit band;
bool respTrama=false;  
bool respEEPROM=false; 
void main()
{

adcon1 = 6; 



/***/
//configuracion de pines para calefactores. 
tris_C1_0 = 0; 
tris_C1_1 = 0; 
tris_C2_0 = 0; 	
tris_C2_1 = 0; 


//pines para extractores 8
tris_E1_0 = 0; 
tris_E1_1 = 0;
tris_E1_2 = 0; 
tris_E1_3 = 0;

tris_E2_0 = 0; 
tris_E2_1 = 0;
tris_E2_2 = 0; 
tris_E2_3 = 0;

//pines para Aire Acondicionado 4
tris_AA_0 = 0; 
tris_AA_1 = 0; 
tris_AA_2 = 0; 
tris_AA_3 = 0; 

//pines para persiana
tris_per_0 = 0; 
tris_per_1 = 0; 

//pines para bombillos
tris_port_Bomb = 0; 
tris_port_Bomb6 = 0;
tris_port_Bomb7 = 0;  

//pin para led indicador de recepcion de trama
tris_indRecTrama  = 0; 
pin_indRecTrama = 0; 

/***/

 
	
	porta = 0x00; 	
	portb = 0; 

	
	char a = 0x0; 
	bool x=false; 
	uart_init(1,129);  // set high speed divisor mode and divisor value
	
	
	//delay_ms(10); 
	//puts("Bienvenido a este programa \n"); 
	puts("H1"); 
	//ResetPuertos();
	limpiarVariables(); 
	while (1)
	{
		
		//portb = ESTADO; 
		if (kbhit())
		{
			temp = getc(); 
			//putc(temp);
			UltValor = temp; 
			
			switch(ESTADO)
			{
				case INICIO:
					if((temp == 'P') || (temp == 'p'))
						ESTADO = CABECERA_P; 
					//puts("Estado sig: CABECERA_P\n");
					UltValor = temp; 
					/*if((temp == 'x'))
						setPuertos(); 
					if((temp == 'r'))
						ResetPuertos(); 
					if((temp == 'A'))
					{
						AA = 15;  
						setPuertos(); 
					}
					
					if((temp=='b'))
						{
							port_Bomb = 0xFF; 
							port_Bomb6 = 1; 
							port_Bomb7 = 1; 
						}
					*/	
					break; 
					
				case CABECERA_P:
					if((temp == 'A') || (temp == 'a'))
						ESTADO = CABECERA_A; 
					else 
						ESTADO = INICIO; 
						
					//puts("Estado sig: CABECERA_A"); 
				break; 
					
				case CABECERA_A:
					LongTrama = temp; 					
					ESTADO = LONGITUD;
					
					//puts("Estado sig: Longitud");
				break;  
				
				case LONGITUD: 
					Origen = temp; 
					ESTADO = ORIGEN; 
					//puts("Estado sig: Origen");
				break; 
				
				case ORIGEN: 
					Destino = temp; 
					if(Destino == DirHABITACION){
						ESTADO = DESTINO; 
						pin_indRecTrama = 1; 
					}
					else 
					{
						ESTADO = INICIO; 
						pin_indRecTrama = 0; 
					}
					//puts("Estado sig: Destino");
					
				break; 
				
				case DESTINO: 
					RegEstado = temp; 
					
					if(RegEstado == 1)
						respTrama = true;  
					else												
						respTrama = false; 	
					
					if(RegEstado == 2)
						respEEPROM = true; 
					else 
						respEEPROM = false; 
					
					ESTADO = vESTADO; 
					//puts("Estado sig: vEstado");
				break; 
				
				case vESTADO: 
					nom_temp = temp;
					nom_val = 1; 
														
					if((temp == 'M') || (temp == 'm'))
						ESTADO = FIN_CAB_M;
					else	
						ESTADO = DISPOSITIVO; 
					//puts("Estado sig: Dispositivo");
				break;
				
				case DISPOSITIVO:
					if(nom_val==2)
					{
						val_temp = temp; 
						SetDispositivo(nom_temp,nom_temp_h,val_temp); 
						nom_val = 0; 
					}
					else if(nom_val == 1)
						
					{ 
						nom_temp_h = temp;
						nom_val = 2; 
					}
					else if(nom_val == 0)
					{
						nom_temp = temp; 
						nom_val = 1; 
					}
				
					if((temp == 'M') || (temp == 'm'))
						ESTADO = FIN_CAB_M;
						//putc(temp); 
						//puts("Estado: DISPOSITIVO"); 
				break;
				
				case FIN_CAB_M:
					if((temp == 'R') || (temp == 'r'))
					{
						setPuertos(); 
						
						if(respTrama)
							ResponderTrama(100); 
						
						if(respEEPROM)
							ResponderEEPROM(100); 
							
						ESTADO = INICIO; 
					}
					else 
					{
					if(nom_val == 2)
					{
						val_temp = temp; 
						SetDispositivo(nom_temp,nom_temp_h,val_temp); 
						nom_val = 0; 
					}
					else if(nom_val == 1)
						
					{ 
						nom_temp_h = temp;
						nom_val = 2; 
					}
					else if(nom_val == 0)
					{
						nom_temp = temp; 
						nom_val = 1; 
					}
				
					
						ESTADO = DISPOSITIVO; 
					}							
					
					//puts("Estado: FIN_CAB_M");
				break; 
				default: 					
					ESTADO = INICIO; 
				break; 
			}//fin switch												
		}// if (kbhit())
	}//fin while(1)
}
