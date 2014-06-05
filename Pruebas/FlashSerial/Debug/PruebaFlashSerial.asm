;/////////////////////////////////////////////////////////////////////////////////
;// Code Generator: BoostC Compiler - http://www.sourceboost.com
;// Version       : 7.04
;// License Type  : Lite License (Unregistered)
;// Limitations   : PIC12,PIC16 max code size:2048 words, max RAM banks:2, Non commercial use only
;/////////////////////////////////////////////////////////////////////////////////

	include "P16F877A.inc"
; Heap block 0, size:96 (0x000000A0 - 0x000000FF)
__HEAP_BLOCK0_BANK               EQU	0x00000001
__HEAP_BLOCK0_START_OFFSET       EQU	0x00000020
__HEAP_BLOCK0_END_OFFSET         EQU	0x0000007F
; Heap block 1, size:7 (0x00000069 - 0x0000006F)
__HEAP_BLOCK1_BANK               EQU	0x00000000
__HEAP_BLOCK1_START_OFFSET       EQU	0x00000069
__HEAP_BLOCK1_END_OFFSET         EQU	0x0000006F
; Heap block 2, size:0 (0x00000000 - 0x00000000)
__HEAP_BLOCK2_BANK               EQU	0x00000000
__HEAP_BLOCK2_START_OFFSET       EQU	0x00000000
__HEAP_BLOCK2_END_OFFSET         EQU	0x00000000
; Heap block 3, size:0 (0x00000000 - 0x00000000)
__HEAP_BLOCK3_BANK               EQU	0x00000000
__HEAP_BLOCK3_START_OFFSET       EQU	0x00000000
__HEAP_BLOCK3_END_OFFSET         EQU	0x00000000
gbl_status                       EQU	0x00000003 ; bytes:1
gbl_indf                         EQU	0x00000000 ; bytes:1
gbl_tmr0                         EQU	0x00000001 ; bytes:1
gbl_pcl                          EQU	0x00000002 ; bytes:1
gbl_fsr                          EQU	0x00000004 ; bytes:1
gbl_porta                        EQU	0x00000005 ; bytes:1
gbl_portb                        EQU	0x00000006 ; bytes:1
gbl_portc                        EQU	0x00000007 ; bytes:1
gbl_portd                        EQU	0x00000008 ; bytes:1
gbl_porte                        EQU	0x00000009 ; bytes:1
gbl_pclath                       EQU	0x0000000A ; bytes:1
gbl_intcon                       EQU	0x0000000B ; bytes:1
gbl_pir1                         EQU	0x0000000C ; bytes:1
gbl_pir2                         EQU	0x0000000D ; bytes:1
gbl_tmr1l                        EQU	0x0000000E ; bytes:1
gbl_tmr1h                        EQU	0x0000000F ; bytes:1
gbl_t1con                        EQU	0x00000010 ; bytes:1
gbl_tmr2                         EQU	0x00000011 ; bytes:1
gbl_t2con                        EQU	0x00000012 ; bytes:1
gbl_sspbuf                       EQU	0x00000013 ; bytes:1
gbl_sspcon                       EQU	0x00000014 ; bytes:1
gbl_ccpr1l                       EQU	0x00000015 ; bytes:1
gbl_ccpr1h                       EQU	0x00000016 ; bytes:1
gbl_ccp1con                      EQU	0x00000017 ; bytes:1
gbl_rcsta                        EQU	0x00000018 ; bytes:1
gbl_txreg                        EQU	0x00000019 ; bytes:1
gbl_rcreg                        EQU	0x0000001A ; bytes:1
gbl_ccpr2l                       EQU	0x0000001B ; bytes:1
gbl_ccpr2h                       EQU	0x0000001C ; bytes:1
gbl_ccp2con                      EQU	0x0000001D ; bytes:1
gbl_adresh                       EQU	0x0000001E ; bytes:1
gbl_adcon0                       EQU	0x0000001F ; bytes:1
gbl_option_reg                   EQU	0x00000081 ; bytes:1
gbl_trisa                        EQU	0x00000085 ; bytes:1
gbl_trisb                        EQU	0x00000086 ; bytes:1
gbl_trisc                        EQU	0x00000087 ; bytes:1
gbl_trisd                        EQU	0x00000088 ; bytes:1
gbl_trise                        EQU	0x00000089 ; bytes:1
gbl_pie1                         EQU	0x0000008C ; bytes:1
gbl_pie2                         EQU	0x0000008D ; bytes:1
gbl_pcon                         EQU	0x0000008E ; bytes:1
gbl_sspcon2                      EQU	0x00000091 ; bytes:1
gbl_pr2                          EQU	0x00000092 ; bytes:1
gbl_sspadd                       EQU	0x00000093 ; bytes:1
gbl_sspstat                      EQU	0x00000094 ; bytes:1
gbl_txsta                        EQU	0x00000098 ; bytes:1
gbl_spbrg                        EQU	0x00000099 ; bytes:1
gbl_cmcon                        EQU	0x0000009C ; bytes:1
gbl_cvrcon                       EQU	0x0000009D ; bytes:1
gbl_adresl                       EQU	0x0000009E ; bytes:1
gbl_adcon1                       EQU	0x0000009F ; bytes:1
gbl_eedata                       EQU	0x0000010C ; bytes:1
gbl_eeadr                        EQU	0x0000010D ; bytes:1
gbl_eedath                       EQU	0x0000010E ; bytes:1
gbl_eeadrh                       EQU	0x0000010F ; bytes:1
gbl_eecon1                       EQU	0x0000018C ; bytes:1
gbl_eecon2                       EQU	0x0000018D ; bytes:1
gbl_sw_SPBRG                     EQU	0x00000047 ; bytes:2
gbl_sw_RCREG                     EQU	0x00000048 ; bytes:2
gbl_sw_TXREG                     EQU	0x00000049 ; bytes:2
gbl_sw_TXSTA                     EQU	0x0000004A ; bytes:2
gbl_sw_RCSTA                     EQU	0x0000004B ; bytes:2
gbl_sw_TXIF_PIR                  EQU	0x0000004C ; bytes:2
gbl_sw_RCIF_PIR                  EQU	0x0000004C ; bytes:2
gbl_calef1                       EQU	0x00000024 ; bytes:1
gbl_calef2                       EQU	0x00000025 ; bytes:1
gbl_Extract1                     EQU	0x00000026 ; bytes:1
gbl_Extract2                     EQU	0x00000027 ; bytes:1
gbl_AA                           EQU	0x00000028 ; bytes:1
gbl_Persiana                     EQU	0x00000029 ; bytes:1
gbl_bombillos                    EQU	0x0000002A ; bytes:1
gbl_RegEstado                    EQU	0x0000002B ; bytes:1
gbl_ESTADO                       EQU	0x0000002C ; bytes:1
gbl_UltValor                     EQU	0x0000002D ; bytes:1
gbl_LongTrama                    EQU	0x0000002E ; bytes:1
gbl_Origen                       EQU	0x0000002F ; bytes:1
gbl_Destino                      EQU	0x00000030 ; bytes:1
gbl_nom_val                      EQU	0x00000031 ; bytes:1
gbl_temp                         EQU	0x00000032 ; bytes:1
gbl_nom_temp                     EQU	0x00000033 ; bytes:1
gbl_nom_temp_h                   EQU	0x00000034 ; bytes:1
gbl_val_temp                     EQU	0x00000035 ; bytes:1
gbl_p1                           EQU	0x00000020 ; bytes:2
CompGblVar56                     EQU	0x00000036 ; bytes:1
CompGblVar57                     EQU	0x00000037 ; bytes:1
gbl_i                            EQU	0x00000022 ; bytes:2
gbl_respTrama                    EQU	0x00000038 ; bit:0
gbl_respEEPROM                   EQU	0x00000038 ; bit:1
CompTempVarRet631                EQU	0x0000003C ; bytes:1
GETC_00000_1_l_rcreg             EQU	0x0000001A ; bytes:1
GETC_00000_1_l_rx                EQU	0x00000007 ; bit:7
GETC_00000_1_mask                EQU	0x0000003B ; bytes:1
CompTempVarRet632                EQU	0x0000003C ; bytes:1
KBHIT_00000_1_l_oerr             EQU	0x00000018 ; bit:1
KBHIT_00000_1_l_cren             EQU	0x00000018 ; bit:4
KBHIT_00000_1_l_rcif             EQU	0x0000000C ; bit:5
KBHIT_00000_1_l_rx               EQU	0x00000007 ; bit:7
puts_00000_arg_source            EQU	0x0000003B ; bytes:2
PUTC_00000_arg_tx_char           EQU	0x00000067 ; bytes:1
PUTC_00000_1_l_txreg             EQU	0x00000019 ; bytes:1
PUTC_00000_1_l_txif              EQU	0x0000000C ; bit:4
PUTC_00000_1_l_tx                EQU	0x00000007 ; bit:6
PUTC_00000_1_mask                EQU	0x00000068 ; bytes:1
puts__00000_arg_source           EQU	0x00000061 ; bytes:2
puts__00000_arg_tam              EQU	0x00000063 ; bytes:2
puts__00000_1_i                  EQU	0x00000065 ; bytes:2
CompTempVar633                   EQU	0x00000067 ; bytes:1
SetDisposi_00015_arg_n1          EQU	0x0000003B ; bytes:1
SetDisposi_00015_arg_n2          EQU	0x0000003C ; bytes:1
SetDisposi_00015_arg_val         EQU	0x0000003D ; bytes:1
ResponderT_00018_arg_Destino     EQU	0x0000003B ; bytes:1
ResponderT_00018_1_trama         EQU	0x0000003C ; bytes:2
CompTempVar634                   EQU	0x0000003E ; bytes:9
ResponderE_00019_arg_Destino     EQU	0x0000003B ; bytes:1
ResponderE_00019_1_trama         EQU	0x0000003C ; bytes:2
CompTempVar640                   EQU	0x0000004E ; bytes:19
main_1_a                         EQU	0x00000039 ; bytes:1
main_1_x                         EQU	0x0000003A ; bit:0
CompTempVar656                   EQU	0x0000003D ; bytes:3
UART_INIT_00000_arg_BRG_mode     EQU	0x0000003B ; bytes:1
UART_INIT_00000_arg_BRG_divisor  EQU	0x0000003C ; bytes:1
UART_INIT_00000_1_dummy          EQU	0x0000003D ; bytes:1
UART_INIT_00000_1_l_spbrg        EQU	0x00000099 ; bytes:1
UART_INIT_00000_1_l_txsta        EQU	0x00000098 ; bytes:1
UART_INIT_00000_1_l_rcsta        EQU	0x00000018 ; bytes:1
UART_INIT_00000_1_l_rcreg        EQU	0x0000001A ; bytes:1
UART_INIT_00000_1_l_tx_tris      EQU	0x00000087 ; bit:6
UART_INIT_00000_1_l_rx_tris      EQU	0x00000087 ; bit:7
UART_INIT_00000_1_l_tx           EQU	0x00000007 ; bit:6
UART_INIT_00000_1_l_cren         EQU	0x00000018 ; bit:4
UART_INIT_00000_1_l_brgh         EQU	0x00000098 ; bit:2
eeprom_rea_00011_arg_address     EQU	0x0000003E ; bytes:1
CompTempVarRet658                EQU	0x0000003F ; bytes:1
eeprom_wri_00012_arg_address     EQU	0x0000003E ; bytes:1
eeprom_wri_00012_arg_data        EQU	0x0000003F ; bytes:1
eeprom_wri_00012_1_intState      EQU	0x0000003A ; bit:1
delay_ms_00000_arg_del           EQU	0x0000003C ; bytes:1
delay_s_00000_arg_del            EQU	0x0000003B ; bytes:1
	ORG 0x00000000
	GOTO	_startup
	ORG 0x00000004
delay_ms_00000
; { delay_ms ; function begin
	MOVF delay_ms_00000_arg_del, F
	BTFSS STATUS,Z
	GOTO	label1
	RETURN
label1
	MOVLW 0xF9
label2
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	ADDLW 0xFF
	BTFSS STATUS,Z
	GOTO	label2
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	DECFSZ delay_ms_00000_arg_del, F
	GOTO	label1
	RETURN
; } delay_ms function end

	ORG 0x00000030
delay_s_00000
; { delay_s ; function begin
label3
	MOVLW 0xFA
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	MOVLW 0xFA
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	MOVLW 0xFA
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	MOVLW 0xFA
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	DECFSZ delay_s_00000_arg_del, F
	GOTO	label3
	RETURN
; } delay_s function end

	ORG 0x0000003F
PUTC_00000
; { PUTC ; function begin
	MOVLW 0x01
	MOVWF PUTC_00000_1_mask
label4
	BTFSS PUTC_00000_1_l_txif,4
	GOTO	label4
	MOVF PUTC_00000_arg_tx_char, W
	MOVWF PUTC_00000_1_l_txreg
	RETURN
; } PUTC function end

	ORG 0x00000046
puts__00000
; { puts_ ; function begin
	CLRF puts__00000_1_i
	CLRF puts__00000_1_i+D'1'
	CLRF puts__00000_1_i
	CLRF puts__00000_1_i+D'1'
label5
	MOVF puts__00000_1_i+D'1', W
	XORLW 0x80
	MOVWF CompTempVar633
	MOVF puts__00000_arg_tam+D'1', W
	XORLW 0x80
	SUBWF CompTempVar633, W
	BTFSS STATUS,Z
	GOTO	label6
	MOVF puts__00000_arg_tam, W
	SUBWF puts__00000_1_i, W
label6
	BTFSC STATUS,C
	RETURN
	BCF STATUS,IRP
	BTFSC puts__00000_arg_source+D'1',0
	BSF STATUS,IRP
	MOVF puts__00000_arg_source, W
	MOVWF FSR
	INCF puts__00000_arg_source, F
	BTFSC STATUS,Z
	INCF puts__00000_arg_source+D'1', F
	MOVF INDF, W
	MOVWF PUTC_00000_arg_tx_char
	CALL PUTC_00000
	INCF puts__00000_1_i, F
	BTFSC STATUS,Z
	INCF puts__00000_1_i+D'1', F
	GOTO	label5
; } puts_ function end

	ORG 0x00000065
eeprom_wri_00012
; { eeprom_write ; function begin
label7
	BSF STATUS, RP0
	BSF STATUS, RP1
	BTFSC gbl_eecon1,1
	GOTO	label7
	BCF STATUS, RP0
	BCF STATUS, RP1
	MOVF eeprom_wri_00012_arg_address, W
	BSF STATUS, RP1
	MOVWF gbl_eeadr
	BCF STATUS, RP1
	MOVF eeprom_wri_00012_arg_data, W
	BSF STATUS, RP1
	MOVWF gbl_eedata
	BSF STATUS, RP0
	BCF gbl_eecon1,7
	BSF gbl_eecon1,2
	BCF STATUS, RP0
	BCF STATUS, RP1
	BCF eeprom_wri_00012_1_intState,1
	BTFSC gbl_intcon,7
	BSF eeprom_wri_00012_1_intState,1
	BCF gbl_intcon,7
	MOVLW 0x55
	BSF STATUS, RP0
	BSF STATUS, RP1
	MOVWF gbl_eecon2
	MOVLW 0xAA
	MOVWF gbl_eecon2
	BSF gbl_eecon1,1
	BCF gbl_eecon1,2
	BCF STATUS, RP0
	BCF STATUS, RP1
	BTFSC eeprom_wri_00012_1_intState,1
	BSF gbl_intcon,7
	BTFSS eeprom_wri_00012_1_intState,1
	BCF gbl_intcon,7
	RETURN
; } eeprom_write function end

	ORG 0x0000008A
eeprom_rea_00011
; { eeprom_read ; function begin
label8
	BSF STATUS, RP0
	BSF STATUS, RP1
	BTFSC gbl_eecon1,1
	GOTO	label8
	BCF STATUS, RP0
	BCF STATUS, RP1
	MOVF eeprom_rea_00011_arg_address, W
	BSF STATUS, RP1
	MOVWF gbl_eeadr
	BSF STATUS, RP0
	BCF gbl_eecon1,7
	BSF gbl_eecon1,0
	BCF STATUS, RP0
	MOVF gbl_eedata, W
	BCF STATUS, RP1
	MOVWF CompTempVarRet658
	RETURN
; } eeprom_read function end

	ORG 0x0000009B
KBHIT_00000
; { KBHIT ; function begin
	BCF STATUS, RP0
	BCF STATUS, RP1
	BTFSS KBHIT_00000_1_l_oerr,1
	GOTO	label9
	BCF KBHIT_00000_1_l_cren,4
	BSF KBHIT_00000_1_l_cren,4
label9
	CLRF CompTempVarRet632
	BTFSC KBHIT_00000_1_l_rcif,5
	INCF CompTempVarRet632, F
	RETURN
; } KBHIT function end

	ORG 0x000000A5
setPuertos_00000
; { setPuertos ; function begin
	BCF STATUS, RP0
	BCF STATUS, RP1
	BTFSC gbl_calef1,0
	BSF gbl_portb,0
	BTFSS gbl_calef1,0
	BCF gbl_portb,0
	BTFSC gbl_calef1,1
	BSF gbl_portb,1
	BTFSS gbl_calef1,1
	BCF gbl_portb,1
	BTFSC gbl_calef2,0
	BSF gbl_portb,2
	BTFSS gbl_calef2,0
	BCF gbl_portb,2
	BTFSC gbl_calef2,1
	BSF gbl_portb,3
	BTFSS gbl_calef2,1
	BCF gbl_portb,3
	BTFSC gbl_Extract1,0
	BSF gbl_portd,4
	BTFSS gbl_Extract1,0
	BCF gbl_portd,4
	BTFSC gbl_Extract1,1
	BSF gbl_portd,5
	BTFSS gbl_Extract1,1
	BCF gbl_portd,5
	BTFSC gbl_Extract1,2
	BSF gbl_portd,6
	BTFSS gbl_Extract1,2
	BCF gbl_portd,6
	BTFSC gbl_Extract1,3
	BSF gbl_portd,7
	BTFSS gbl_Extract1,3
	BCF gbl_portd,7
	BTFSC gbl_Extract2,0
	BSF gbl_portd,0
	BTFSS gbl_Extract2,0
	BCF gbl_portd,0
	BTFSC gbl_Extract2,1
	BSF gbl_portd,1
	BTFSS gbl_Extract2,1
	BCF gbl_portd,1
	BTFSC gbl_Extract2,2
	BSF gbl_portd,2
	BTFSS gbl_Extract2,2
	BCF gbl_portd,2
	BTFSC gbl_Extract2,3
	BSF gbl_portd,3
	BTFSS gbl_Extract2,3
	BCF gbl_portd,3
	DECF gbl_AA, W
	BTFSS STATUS,Z
	GOTO	label10
	BSF gbl_portb,7
	MOVLW 0xBC
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	BCF gbl_portb,7
	MOVLW 0x02
	MOVWF delay_s_00000_arg_del
	CALL delay_s_00000
	BSF gbl_portb,6
	MOVLW 0xBC
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	BCF gbl_portb,6
	MOVLW 0x02
	MOVWF delay_s_00000_arg_del
	CALL delay_s_00000
	BSF gbl_portb,6
	MOVLW 0xBC
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	BCF gbl_portb,6
	GOTO	label12
label10
	MOVF gbl_AA, W
	XORLW 0x02
	BTFSS STATUS,Z
	GOTO	label11
	BSF gbl_portb,5
	MOVLW 0xBC
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	BCF gbl_portb,5
	MOVLW 0x01
	MOVWF delay_s_00000_arg_del
	CALL delay_s_00000
	GOTO	label12
label11
	MOVF gbl_AA, W
	XORLW 0x03
	BTFSS STATUS,Z
	GOTO	label12
	BSF gbl_portb,4
	MOVLW 0xBC
	MOVWF delay_ms_00000_arg_del
	CALL delay_ms_00000
	BCF gbl_portb,4
	MOVLW 0x01
	MOVWF delay_s_00000_arg_del
	CALL delay_s_00000
label12
	BTFSC gbl_Persiana,0
	BSF gbl_portc,4
	BTFSS gbl_Persiana,0
	BCF gbl_portc,4
	BTFSC gbl_Persiana,1
	BSF gbl_portc,5
	BTFSS gbl_Persiana,1
	BCF gbl_portc,5
	BTFSC gbl_bombillos,0
	BSF gbl_porta,0
	BTFSS gbl_bombillos,0
	BCF gbl_porta,0
	BTFSC gbl_bombillos,1
	BSF gbl_porta,1
	BTFSS gbl_bombillos,1
	BCF gbl_porta,1
	BTFSC gbl_bombillos,2
	BSF gbl_porta,2
	BTFSS gbl_bombillos,2
	BCF gbl_porta,2
	BTFSC gbl_bombillos,3
	BSF gbl_porta,3
	BTFSS gbl_bombillos,3
	BCF gbl_porta,3
	BTFSC gbl_bombillos,4
	BSF gbl_porta,4
	BTFSS gbl_bombillos,4
	BCF gbl_porta,4
	BTFSC gbl_bombillos,5
	BSF gbl_porta,5
	BTFSS gbl_bombillos,5
	BCF gbl_porta,5
	BTFSC gbl_bombillos,6
	BSF gbl_porte,0
	BTFSS gbl_bombillos,6
	BCF gbl_porte,0
	BTFSC gbl_bombillos,7
	BSF gbl_porte,1
	BTFSS gbl_bombillos,7
	BCF gbl_porte,1
	RETURN
; } setPuertos function end

	ORG 0x00000132
puts_00000
; { puts ; function begin
label13
	BCF STATUS,IRP
	BTFSC puts_00000_arg_source+D'1',0
	BSF STATUS,IRP
	MOVF puts_00000_arg_source, W
	MOVWF FSR
	MOVF INDF, F
	BTFSC STATUS,Z
	GOTO	label14
	BCF STATUS,IRP
	BTFSC puts_00000_arg_source+D'1',0
	BSF STATUS,IRP
	MOVF puts_00000_arg_source, W
	MOVWF FSR
	INCF puts_00000_arg_source, F
	BTFSC STATUS,Z
	INCF puts_00000_arg_source+D'1', F
	MOVF INDF, W
	MOVWF PUTC_00000_arg_tx_char
	CALL PUTC_00000
	GOTO	label13
label14
	MOVLW 0x0D
	MOVWF PUTC_00000_arg_tx_char
	CALL PUTC_00000
	MOVLW 0x0A
	MOVWF PUTC_00000_arg_tx_char
	CALL PUTC_00000
	RETURN
; } puts function end

	ORG 0x0000014D
limpiarVar_00017
; { limpiarVariables ; function begin
	BCF STATUS, RP0
	BCF STATUS, RP1
	CLRF gbl_calef1
	CLRF gbl_calef2
	CLRF gbl_Extract1
	CLRF gbl_Extract2
	CLRF gbl_AA
	CLRF gbl_Persiana
	CLRF gbl_bombillos
	RETURN
; } limpiarVariables function end

	ORG 0x00000157
UART_INIT_00000
; { UART_INIT ; function begin
	BSF STATUS, RP0
	BSF UART_INIT_00000_1_l_rx_tris,7
	BCF UART_INIT_00000_1_l_tx_tris,6
	BCF STATUS, RP0
	MOVF UART_INIT_00000_arg_BRG_divisor, W
	BSF STATUS, RP0
	MOVWF UART_INIT_00000_1_l_spbrg
	MOVLW 0xA4
	MOVWF UART_INIT_00000_1_l_txsta
	BCF STATUS, RP0
	MOVF UART_INIT_00000_arg_BRG_mode, F
	BTFSC STATUS,Z
	GOTO	label15
	BSF STATUS, RP0
	BSF UART_INIT_00000_1_l_brgh,2
	GOTO	label16
label15
	BSF STATUS, RP0
	BCF UART_INIT_00000_1_l_brgh,2
label16
	MOVLW 0xB0
	BCF STATUS, RP0
	MOVWF UART_INIT_00000_1_l_rcsta
	BCF UART_INIT_00000_1_l_cren,4
	BSF UART_INIT_00000_1_l_cren,4
	MOVF UART_INIT_00000_1_l_rcreg, W
	MOVWF UART_INIT_00000_1_dummy
	RETURN
; } UART_INIT function end

	ORG 0x00000171
SetDisposi_00015
; { SetDispositivo ; function begin
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x43
	BTFSS STATUS,Z
	GOTO	label17
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x31
	BTFSS STATUS,Z
	GOTO	label17
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_calef1
	MOVLW 0x03
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
label17
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x43
	BTFSS STATUS,Z
	GOTO	label18
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x32
	BTFSS STATUS,Z
	GOTO	label18
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_calef2
	MOVLW 0x04
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
label18
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x45
	BTFSS STATUS,Z
	GOTO	label19
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x31
	BTFSS STATUS,Z
	GOTO	label19
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_Extract1
	MOVLW 0x06
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
label19
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x45
	BTFSS STATUS,Z
	GOTO	label20
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x32
	BTFSS STATUS,Z
	GOTO	label20
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_Extract2
	MOVLW 0x07
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
label20
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x41
	BTFSS STATUS,Z
	GOTO	label21
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x41
	BTFSS STATUS,Z
	GOTO	label21
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_AA
	MOVLW 0x05
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
label21
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x50
	BTFSS STATUS,Z
	GOTO	label22
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x31
	BTFSS STATUS,Z
	GOTO	label22
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_Persiana
	MOVLW 0x09
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
label22
	MOVF SetDisposi_00015_arg_n1, W
	XORLW 0x42
	BTFSS STATUS,Z
	RETURN
	MOVF SetDisposi_00015_arg_n2, W
	XORLW 0x58
	BTFSS STATUS,Z
	RETURN
	MOVF SetDisposi_00015_arg_val, W
	MOVWF gbl_bombillos
	MOVLW 0x08
	MOVWF eeprom_wri_00012_arg_address
	MOVF SetDisposi_00015_arg_val, W
	MOVWF eeprom_wri_00012_arg_data
	CALL eeprom_wri_00012
	BCF gbl_pir2,4
	RETURN
; } SetDispositivo function end

	ORG 0x000001E2
ResponderT_00018
; { ResponderTrama ; function begin
	MOVLW 0x50
	MOVWF CompTempVar634
	MOVLW 0x41
	MOVWF CompTempVar634+D'1'
	MOVLW 0x4C
	MOVWF CompTempVar634+D'2'
	MOVLW 0x4F
	MOVWF CompTempVar634+D'3'
	MOVLW 0x44
	MOVWF CompTempVar634+D'4'
	MOVLW 0x45
	MOVWF CompTempVar634+D'5'
	MOVLW 0x4D
	MOVWF CompTempVar634+D'6'
	MOVLW 0x52
	MOVWF CompTempVar634+D'7'
	CLRF CompTempVar634+D'8'
	MOVLW HIGH(CompTempVar634+D'0')
	MOVWF ResponderT_00018_1_trama+D'1'
	MOVLW LOW(CompTempVar634+D'0')
	MOVWF ResponderT_00018_1_trama
	BCF STATUS,IRP
	BTFSC ResponderT_00018_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderT_00018_1_trama, W
	MOVWF FSR
	MOVLW 0x02
	ADDWF FSR, F
	MOVLW 0x00
	MOVWF INDF
	MOVF ResponderT_00018_1_trama, W
	MOVWF FSR
	MOVLW 0x03
	ADDWF FSR, F
	MOVLW 0x66
	MOVWF INDF
	MOVF ResponderT_00018_1_trama, W
	MOVWF FSR
	MOVLW 0x04
	ADDWF FSR, F
	MOVF ResponderT_00018_arg_Destino, W
	MOVWF INDF
	MOVF ResponderT_00018_1_trama, W
	MOVWF FSR
	MOVLW 0x05
	ADDWF FSR, F
	MOVLW 0x02
	MOVWF INDF
	MOVF ResponderT_00018_1_trama, W
	MOVWF puts__00000_arg_source
	MOVF ResponderT_00018_1_trama+D'1', W
	MOVWF puts__00000_arg_source+D'1'
	MOVLW 0x08
	MOVWF puts__00000_arg_tam
	CLRF puts__00000_arg_tam+D'1'
	CALL puts__00000
	RETURN
; } ResponderTrama function end

	ORG 0x0000021B
ResponderE_00019
; { ResponderEEPROM ; function begin
	MOVLW 0x41
	MOVWF CompTempVar640+D'1'
	MOVLW 0x44
	MOVWF CompTempVar640+D'4'
	MOVLW 0x45
	MOVWF CompTempVar640+D'5'
	MOVLW 0x4C
	MOVWF CompTempVar640+D'2'
	MOVLW 0x4D
	MOVWF CompTempVar640+D'16'
	MOVLW 0x4F
	MOVWF CompTempVar640+D'3'
	MOVLW 0x50
	MOVWF CompTempVar640
	MOVLW 0x52
	MOVWF CompTempVar640+D'17'
	MOVLW 0x58
	MOVWF CompTempVar640+D'6'
	MOVWF CompTempVar640+D'7'
	MOVWF CompTempVar640+D'8'
	MOVWF CompTempVar640+D'9'
	MOVWF CompTempVar640+D'10'
	MOVWF CompTempVar640+D'11'
	MOVWF CompTempVar640+D'12'
	MOVWF CompTempVar640+D'13'
	MOVWF CompTempVar640+D'14'
	MOVWF CompTempVar640+D'15'
	CLRF CompTempVar640+D'18'
	MOVLW HIGH(CompTempVar640+D'0')
	MOVWF ResponderE_00019_1_trama+D'1'
	MOVLW LOW(CompTempVar640+D'0')
	MOVWF ResponderE_00019_1_trama
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x02
	ADDWF FSR, F
	MOVLW 0x0A
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x03
	ADDWF FSR, F
	MOVLW 0x66
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x04
	ADDWF FSR, F
	MOVF ResponderE_00019_arg_Destino, W
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x05
	ADDWF FSR, F
	MOVLW 0x02
	MOVWF INDF
	MOVLW 0x03
	MOVWF eeprom_rea_00011_arg_address
	CALL eeprom_rea_00011
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x06
	ADDWF FSR, F
	MOVF CompTempVarRet658, W
	MOVWF INDF
	MOVLW 0x04
	MOVWF eeprom_rea_00011_arg_address
	CALL eeprom_rea_00011
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x07
	ADDWF FSR, F
	MOVF CompTempVarRet658, W
	MOVWF INDF
	MOVLW 0x06
	MOVWF eeprom_rea_00011_arg_address
	CALL eeprom_rea_00011
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x08
	ADDWF FSR, F
	MOVF CompTempVarRet658, W
	MOVWF INDF
	MOVLW 0x07
	MOVWF eeprom_rea_00011_arg_address
	CALL eeprom_rea_00011
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x09
	ADDWF FSR, F
	MOVF CompTempVarRet658, W
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x0A
	ADDWF FSR, F
	MOVLW 0x00
	MOVWF INDF
	MOVLW 0x09
	MOVWF eeprom_rea_00011_arg_address
	CALL eeprom_rea_00011
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x0B
	ADDWF FSR, F
	MOVF CompTempVarRet658, W
	MOVWF INDF
	MOVLW 0x08
	MOVWF eeprom_rea_00011_arg_address
	CALL eeprom_rea_00011
	BCF STATUS,IRP
	BTFSC ResponderE_00019_1_trama+D'1',0
	BSF STATUS,IRP
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x0C
	ADDWF FSR, F
	MOVF CompTempVarRet658, W
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x0D
	ADDWF FSR, F
	MOVLW 0x00
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x0E
	ADDWF FSR, F
	MOVLW 0x00
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF FSR
	MOVLW 0x0F
	ADDWF FSR, F
	MOVLW 0x00
	MOVWF INDF
	MOVF ResponderE_00019_1_trama, W
	MOVWF puts__00000_arg_source
	MOVF ResponderE_00019_1_trama+D'1', W
	MOVWF puts__00000_arg_source+D'1'
	MOVLW 0x12
	MOVWF puts__00000_arg_tam
	CLRF puts__00000_arg_tam+D'1'
	CALL puts__00000
	RETURN
; } ResponderEEPROM function end

	ORG 0x000002BF
GETC_00000
; { GETC ; function begin
	MOVLW 0x01
	BCF STATUS, RP0
	BCF STATUS, RP1
	MOVWF GETC_00000_1_mask
label23
	CALL KBHIT_00000
	MOVF CompTempVarRet632, F
	BTFSS STATUS,Z
	GOTO	label24
	CLRWDT
	GOTO	label23
label24
	MOVF GETC_00000_1_l_rcreg, W
	MOVWF CompTempVarRet631
	RETURN
; } GETC function end

	ORG 0x000002CC
main
; { main ; function begin
	MOVLW 0x06
	BSF STATUS, RP0
	BCF STATUS, RP1
	MOVWF gbl_adcon1
	BCF gbl_trisb,0
	BCF gbl_trisb,1
	BCF gbl_trisb,2
	BCF gbl_trisb,3
	BCF gbl_trisd,4
	BCF gbl_trisd,5
	BCF gbl_trisd,6
	BCF gbl_trisd,7
	BCF gbl_trisd,0
	BCF gbl_trisd,1
	BCF gbl_trisd,2
	BCF gbl_trisd,3
	BCF gbl_trisb,4
	BCF gbl_trisb,5
	BCF gbl_trisb,6
	BCF gbl_trisb,7
	BCF gbl_trisc,4
	BCF gbl_trisc,5
	CLRF gbl_trisa
	BCF gbl_trise,0
	BCF gbl_trise,1
	BCF gbl_trisc,2
	BCF STATUS, RP0
	BCF gbl_portc,2
	CLRF gbl_porta
	CLRF gbl_portb
	CLRF main_1_a
	BCF main_1_x,0
	MOVLW 0x01
	MOVWF UART_INIT_00000_arg_BRG_mode
	MOVLW 0x81
	MOVWF UART_INIT_00000_arg_BRG_divisor
	CALL UART_INIT_00000
	MOVLW 0x48
	MOVWF CompTempVar656
	MOVLW 0x31
	MOVWF CompTempVar656+D'1'
	CLRF CompTempVar656+D'2'
	MOVLW HIGH(CompTempVar656+D'0')
	MOVWF puts_00000_arg_source+D'1'
	MOVLW LOW(CompTempVar656+D'0')
	MOVWF puts_00000_arg_source
	CALL puts_00000
	CALL limpiarVar_00017
label25
	CALL KBHIT_00000
	MOVF CompTempVarRet632, F
	BTFSC STATUS,Z
	GOTO	label25
	CALL GETC_00000
	MOVF CompTempVarRet631, W
	MOVWF gbl_temp
	MOVF gbl_temp, W
	MOVWF gbl_UltValor
	MOVF gbl_ESTADO, W
	XORLW 0x00
	BTFSC STATUS,Z
	GOTO	label26
	XORLW 0x01
	BTFSC STATUS,Z
	GOTO	label29
	XORLW 0x03
	BTFSC STATUS,Z
	GOTO	label32
	XORLW 0x01
	BTFSC STATUS,Z
	GOTO	label33
	XORLW 0x07
	BTFSC STATUS,Z
	GOTO	label34
	XORLW 0x01
	BTFSC STATUS,Z
	GOTO	label36
	XORLW 0x03
	BTFSC STATUS,Z
	GOTO	label41
	XORLW 0x01
	BTFSC STATUS,Z
	GOTO	label44
	XORLW 0x0F
	BTFSC STATUS,Z
	GOTO	label49
	GOTO	label57
label26
	MOVF gbl_temp, W
	XORLW 0x50
	BTFSC STATUS,Z
	GOTO	label27
	MOVF gbl_temp, W
	XORLW 0x70
	BTFSS STATUS,Z
	GOTO	label28
label27
	MOVLW 0x01
	MOVWF gbl_ESTADO
label28
	MOVF gbl_temp, W
	MOVWF gbl_UltValor
	GOTO	label25
label29
	MOVF gbl_temp, W
	XORLW 0x41
	BTFSC STATUS,Z
	GOTO	label30
	MOVF gbl_temp, W
	XORLW 0x61
	BTFSS STATUS,Z
	GOTO	label31
label30
	MOVLW 0x02
	MOVWF gbl_ESTADO
	GOTO	label25
label31
	CLRF gbl_ESTADO
	GOTO	label25
label32
	MOVF gbl_temp, W
	MOVWF gbl_LongTrama
	MOVLW 0x03
	MOVWF gbl_ESTADO
	GOTO	label25
label33
	MOVF gbl_temp, W
	MOVWF gbl_Origen
	MOVLW 0x04
	MOVWF gbl_ESTADO
	GOTO	label25
label34
	MOVF gbl_temp, W
	MOVWF gbl_Destino
	MOVF gbl_Destino, W
	XORLW 0x66
	BTFSS STATUS,Z
	GOTO	label35
	MOVLW 0x05
	MOVWF gbl_ESTADO
	BSF gbl_portc,2
	GOTO	label25
label35
	CLRF gbl_ESTADO
	BCF gbl_portc,2
	GOTO	label25
label36
	MOVF gbl_temp, W
	MOVWF gbl_RegEstado
	DECF gbl_RegEstado, W
	BTFSS STATUS,Z
	GOTO	label37
	BSF gbl_respTrama,0
	GOTO	label38
label37
	BCF gbl_respTrama,0
label38
	MOVF gbl_RegEstado, W
	XORLW 0x02
	BTFSS STATUS,Z
	GOTO	label39
	BSF gbl_respEEPROM,1
	GOTO	label40
label39
	BCF gbl_respEEPROM,1
label40
	MOVLW 0x06
	MOVWF gbl_ESTADO
	GOTO	label25
label41
	MOVF gbl_temp, W
	MOVWF gbl_nom_temp
	MOVLW 0x01
	MOVWF gbl_nom_val
	MOVF gbl_temp, W
	XORLW 0x4D
	BTFSC STATUS,Z
	GOTO	label42
	MOVF gbl_temp, W
	XORLW 0x6D
	BTFSS STATUS,Z
	GOTO	label43
label42
	MOVLW 0x08
	MOVWF gbl_ESTADO
	GOTO	label25
label43
	MOVLW 0x07
	MOVWF gbl_ESTADO
	GOTO	label25
label44
	MOVF gbl_nom_val, W
	XORLW 0x02
	BTFSS STATUS,Z
	GOTO	label45
	MOVF gbl_temp, W
	MOVWF gbl_val_temp
	MOVF gbl_nom_temp, W
	MOVWF SetDisposi_00015_arg_n1
	MOVF gbl_nom_temp_h, W
	MOVWF SetDisposi_00015_arg_n2
	MOVF gbl_val_temp, W
	MOVWF SetDisposi_00015_arg_val
	CALL SetDisposi_00015
	CLRF gbl_nom_val
	GOTO	label47
label45
	DECF gbl_nom_val, W
	BTFSS STATUS,Z
	GOTO	label46
	MOVF gbl_temp, W
	MOVWF gbl_nom_temp_h
	MOVLW 0x02
	MOVWF gbl_nom_val
	GOTO	label47
label46
	MOVF gbl_nom_val, F
	BTFSS STATUS,Z
	GOTO	label47
	MOVF gbl_temp, W
	MOVWF gbl_nom_temp
	MOVLW 0x01
	MOVWF gbl_nom_val
label47
	MOVF gbl_temp, W
	XORLW 0x4D
	BTFSC STATUS,Z
	GOTO	label48
	MOVF gbl_temp, W
	XORLW 0x6D
	BTFSS STATUS,Z
	GOTO	label25
label48
	MOVLW 0x08
	MOVWF gbl_ESTADO
	GOTO	label25
label49
	MOVF gbl_temp, W
	XORLW 0x52
	BTFSC STATUS,Z
	GOTO	label50
	MOVF gbl_temp, W
	XORLW 0x72
	BTFSS STATUS,Z
	GOTO	label53
label50
	CALL setPuertos_00000
	BTFSS gbl_respTrama,0
	GOTO	label51
	MOVLW 0x64
	MOVWF ResponderT_00018_arg_Destino
	CALL ResponderT_00018
label51
	BTFSS gbl_respEEPROM,1
	GOTO	label52
	MOVLW 0x64
	MOVWF ResponderE_00019_arg_Destino
	CALL ResponderE_00019
label52
	CLRF gbl_ESTADO
	GOTO	label25
label53
	MOVF gbl_nom_val, W
	XORLW 0x02
	BTFSS STATUS,Z
	GOTO	label54
	MOVF gbl_temp, W
	MOVWF gbl_val_temp
	MOVF gbl_nom_temp, W
	MOVWF SetDisposi_00015_arg_n1
	MOVF gbl_nom_temp_h, W
	MOVWF SetDisposi_00015_arg_n2
	MOVF gbl_val_temp, W
	MOVWF SetDisposi_00015_arg_val
	CALL SetDisposi_00015
	CLRF gbl_nom_val
	GOTO	label56
label54
	DECF gbl_nom_val, W
	BTFSS STATUS,Z
	GOTO	label55
	MOVF gbl_temp, W
	MOVWF gbl_nom_temp_h
	MOVLW 0x02
	MOVWF gbl_nom_val
	GOTO	label56
label55
	MOVF gbl_nom_val, F
	BTFSS STATUS,Z
	GOTO	label56
	MOVF gbl_temp, W
	MOVWF gbl_nom_temp
	MOVLW 0x01
	MOVWF gbl_nom_val
label56
	MOVLW 0x07
	MOVWF gbl_ESTADO
	GOTO	label25
label57
	CLRF gbl_ESTADO
	GOTO	label25
; } main function end

	ORG 0x000003D8
_startup
	BCF STATUS, RP0
	BCF STATUS, RP1
	CLRF gbl_nom_val
	CLRF CompGblVar56
	MOVLW HIGH(CompGblVar56+D'0')
	MOVWF CompGblVar57
	MOVLW LOW(CompGblVar56+D'0')
	MOVWF gbl_p1
	MOVF CompGblVar57, W
	MOVWF gbl_p1+D'1'
	CLRF gbl_i
	CLRF gbl_i+D'1'
	BCF gbl_respTrama,0
	BCF gbl_respEEPROM,1
	BCF PCLATH,3
	BCF PCLATH,4
	GOTO	main
	ORG 0x00002007
	DW 0x3F32
	END
