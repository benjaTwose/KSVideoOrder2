package VideoOrderPack;


	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

	

	/*
	 * Log
	 * modified: BTCh
	 * args: filePathlog is absolute path + filename
	 * */
	
	
	public class Log{
		
		public static String filePathlog = null;
		public static String logname = null;
		
		
		public static int setfilePathlog(String initfilePathlog,String initlogname ){
			
			filePathlog=initfilePathlog;
			logname = initlogname;
			// Creem el directori si no existeix.
			try {
			    File f = new File(filePathlog);
			    if (!f.exists()){
			    	f.mkdirs();
			    	return 1;
			    	}
			}
			catch (Exception e){
				System.out.println(e);
			return 0;	
			}
			
			return 2;
			
		}
		
		public  static void Missatge(String missatge){	    
			System.out.println(Data() + missatge);	
			escriureMissatge(Data() + missatge);
		}
		
		public static String Data(){
			Calendar ara = Calendar.getInstance();
			return ara.get(Calendar.DAY_OF_MONTH) + "/" + (ara.get(Calendar.MONTH) + 1) + "/" + ara.get(Calendar.YEAR) + " " + ara.get(Calendar.HOUR_OF_DAY) + ":" + ara.get(Calendar.MINUTE) + ":" + ara.get(Calendar.SECOND) + " ";
		}
		
		public static void escriureMissatge(String text) {
			
			//setfilePathlog must be configured firs calling setfilePathlog("new_sbsolute_path_and_filename")
			if (setfilePathlog(filePathlog,logname)>0){
		    
			    // Capturem, o creem si no existeix, el fitxer .log
				FileWriter out = null;
				try {
					out = new FileWriter(filePathlog + File.separator + logname, true);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				// Escrivim el missatge sobre el fitxer .log
				BufferedWriter writer = new BufferedWriter(out);
				try {
					System.out.println(Log.Data() +" ; " +text);
					writer.write(Log.Data() +" ; " +text +"\r\n");
					writer.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

