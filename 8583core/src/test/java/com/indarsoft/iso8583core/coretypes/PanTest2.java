package com.indarsoft.iso8583core.coretypes;

import static org.junit.Assert.*;
import com.indarsoft.iso8583core.app.Application;
import com.indarsoft.iso8583core.app.ApplicationFactory;
import com.indarsoft.iso8583core.app.Config;
import com.indarsoft.iso8583core.coretypes.CoreTypesFactory;
import com.indarsoft.iso8583core.coretypes.F002;
import com.indarsoft.utl.Binary;

import org.junit.Test;

public class PanTest2 {

	public String className = this.getClass().getSimpleName() + "." ;
	
	public String CONFIGFILE = "main.properties";
	public Config cfg = new Config( CONFIGFILE );
	public Application  app	= ApplicationFactory.getMain( cfg) ;
	CoreTypesFactory ctf = new CoreTypesFactory(app);

	@Test
	public void Isvalid() {
		byte[] 	panarr 		= {  
								(byte)0xF1, (byte)0xF4, (byte)0xF3, (byte)0xF4, 
								(byte)0xF5, (byte)0xF6, (byte)0xF7, (byte)0xF8, 
								(byte)0xF9, (byte)0xF0, (byte)0xF1, (byte)0xF2, 
								(byte)0xF3, (byte)0xF4, (byte)0xF5, (byte)0xF6} ;
		
		String	pandatastr 	= "34567890123456" ; // pan without length
		F002 pan 	= ctf.getF002( panarr ) ;
		
		if ( pan.isValid() ){
			String result = pan.data2String() ;
			if ( result.equals(  pandatastr  ) ){
				System.out.println(className+"Isvalid                    : TRUE - " + pandatastr+ " - " + result);
				assertTrue( true) ;
			}else{
				System.out.println(className+"Isvalid                    : FALSE - "+ result);
				System.out.println(className+"Isvalid                         "	+ pandatastr );				
				assertFalse( true) ;
			}
		}else{
			System.out.println(className+"Isvalid                     : FALSE - "+ pan.getStatusMsg() );
			assertFalse( true) ;
		}
	}

	@Test
	public void NotValidDataLength() {
		byte[] 	panarrnv 		= {  
									(byte)0xF1, (byte)0xF2, (byte)0xF3, (byte)0xF4 } ;
				
		String	panarrnvstr 	= Binary.toHexStr( panarrnv ) ;
		F002 pannv	= ctf.getF002( panarrnv ) ;
		if ( ! pannv.isValid() ){
			System.out.println(className+"NotValidDataLength         : TRUE - "+ pannv.getStatusMsg() );
			assertTrue( true) ;

		}else{
			String result = pannv.data2String() ;
			if ( result.equals(  panarrnvstr  ) ){
				System.out.println(className+"NotValidDataLength         : FALSE - " + result);
				assertFalse( true) ;
			}else{
				System.out.println(className+"NotValidDataLength         : FALSE - "+ result);
				assertFalse( true) ;
			}
		}
	}

	@Test
	public void NotValidDataLength2() {
		byte[] 	panvaluenv 		= { (byte)0xF1,(byte)0xF3,(byte)0xF5,(byte)0xF7 } ;
		String	panvaluestrnv 	= Binary.toHexStr( panvaluenv ) ;
		F002 pannv 	= ctf.getF002 ( panvaluenv ) ; 
		if ( ! pannv.isValid() ){
			System.out.println(className+"NotValidDataLength2        : TRUE - "+ pannv.getStatusMsg() );
			assertTrue( true) ;

		}else{
			String result = pannv.data2String() ;
			if ( result.equals(  panvaluestrnv  ) ){
				System.out.println(className+"NotValidDataLength2        : FALSE - " + result);
				assertFalse( true) ;
			}else{
				System.out.println(className+"NotValidDataLength2        : FALSE - "+ result);
				assertFalse( true) ;
			}
		}
	}

	
	@Test
	public void NotValidDataCodification() {
		byte[] 	panvaluenv 		= { 
				                    0x23, 0x45, 0x67, (byte)0x89, 0x01, 0x23, 
				                    0x23, 0x45, 0x67, (byte)0x89, 0x01, 0x23 } ;
		String	panvaluestrnv 	= Binary.toHexStr( panvaluenv ) ;
		F002 pannv 	= ctf.getF002 ( panvaluenv ) ; 
		if ( ! pannv.isValid() ){
			System.out.println(className+"NotValidDataCodification   : TRUE - "+ pannv.getStatusMsg() );
			assertTrue( true) ;

		}else{
			String result = pannv.data2String() ;
			if ( result.equals(  panvaluestrnv  ) ){
				System.out.println("NotValidDataCodification   : FALSE - " + result);
				assertFalse( true) ;
			}else{
				System.out.println("NotValidDataCodification   : FALSE - "+ result);
				assertFalse( true) ;
			}
		}
	}
}
