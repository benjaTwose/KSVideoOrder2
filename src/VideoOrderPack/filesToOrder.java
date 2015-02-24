package VideoOrderPack;

/*
 * Order video.kids files that has been disaster uploaded in ftp server 
 * This will put all video file into his own video dir
 * 
 * 1rst parameter is the base dir to search
 * ex:  "D:\FTPVideos"
 */


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import VideoOrderPack.SwingControlDemo;
import VideoOrderPack.Log;


public class filesToOrder {

	
	public static String baseDirw ;
	public static int cntmove, cntfiles;
	public static SwingControlDemo swingControl = new SwingControlDemo();
	public static Boolean launchwks = false;
	
	
	
    /*
     * main
     * */
    public static void main(String[] args) {
    	        	
        // initialize log
        Log.setfilePathlog(System.getProperty("user.home") , "KSReorderVideos.log");
        	
       	// initialize form
    	swingControl.showTextAreaDemo();
    	
    	// initialize timer launcher
    	Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        
        //Setting the date from when you want to activate scheduling, Everyday by now at 4 AM
      date.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), 
      		Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1, 4, 00);
      
      //execute every 24 h (86400000 ms)
      ProcessingClass timeProcess = new ProcessingClass();
      timer.schedule(timeProcess, date.getTime(), 86400000); 
      
        // do works infinitely
        do{
        	try{
        	
			Thread.currentThread();
			Thread.sleep(5000);

        	}
        	catch (Exception e){
        		e.printStackTrace();
        	}

			filesToOrder.doWorksWalk();
        }while(true);
        
    }
    
    /*
     *  timer launcher
     * */
    public static class ProcessingClass extends TimerTask{
        public void run() {
            System.out.println("The task is called " + new Date());

	    	launchwks = true;
	    	baseDirw = swingControl.txtpathToSearch.getText();
	    	
	    	//
	    	filesToOrder.doWorksWalk();
            
        }
    }
    

    /*
     *  do works, control launch of works
     * */
	public static void doWorksWalk() {

		//do{
			if (swingControl.initwalk){
				launchwks = true;
			}
	    	baseDirw = swingControl.txtpathToSearch.getText();
		//}while (launchwks==false);

			try {
	    	if (baseDirw != null && launchwks ) {
	    		launchwks= false;
	    		swingControl.commentTextArea.setText("... INI REORDER ..." +"\n");
	    		Log.escriureMissatge("... INI REORDER ...");
	    		cntmove=0;
	    		cntfiles=0;
	      		 walkin(new File(baseDirw));
	    	     Log.escriureMissatge("... END REORDER ...");
	    	     swingControl.commentTextArea.append("... END REORDER ..." +"\n");
	    	}
	
			launchwks=false;
			swingControl.initwalk=false;
	    	}
	    	
	    	catch (Exception e) {

				System.out.println("### ERROR: " );
				swingControl.commentTextArea.append( "\n"+"ERROR:" +e + "\n");
				Log.escriureMissatge("ERROR:" +e );
				//e.printStackTrace();
				launchwks=false;
				swingControl.initwalk=false;
	    	}	

	}
	
	
    /*
     *  walking files and reorder to correct directory destine
     * */
	
	public static void walkin(File dir) {

		File listFile[] = dir.listFiles();
        String[] paths = dir.list();
        String[] pathsDir = dir.list(); 
        String[] pathsName = dir.list(); 
        String[] pathsACA = dir.list();
        String[] pathsNumV = dir.list();
        String[] newPathVideo = dir.list();
        Path pathDest = null;
 
        
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
            	cntfiles++;
            	swingControl.lbcntvideos.setText("Progress: "+ cntmove+" / " + cntfiles);
            	
            	Thread.yield();
            	
                if (listFile[i].isDirectory()) {
                    walkin(listFile[i]);
                    //System.out.println("CHANGE DIR --- ");
                    
                } else {
                    paths[i]=listFile[i].getPath();
                    pathsDir[i]=listFile[i].getParent();
                    pathsName[i]=listFile[i].getName();
                    
                    //System.out.println("path: " + paths[i] + " ......... dir: "+ pathsDir[i] + " ......... filename: "+ pathsName[i] );
                    
                    
                    if ( listFile[i].getPath().indexOf("FTPVideos")==3 && listFile[i].getPath().indexOf("kids")>0){
                    	
                    	// System.out.println("walking id: " + i);
                    	// Extract the Aca id value
                    	int idini = listFile[i].getPath().indexOf("\\",9);
                    	int idfin = listFile[i].getPath().indexOf("\\",13);
                    	try {
	                    	pathsACA[i]=paths[i].substring(idini+1,idfin);
	                    	//System.out.println(" aca ->  " + pathsACA[i] );
	                    	
	                    	// Extract the VId id value  1-6165-1.kids79  189-6292.kids
	                       	idini = pathsName[i].indexOf("-",1);
	                       	if ((idfin = pathsName[i].indexOf("-",idini+1))==-1) 
	                       	{
	                       		idfin = pathsName[i].indexOf(".",idini+1);
	                        	
	                       	}
	                    	
	                    	
	                       	pathsNumV[i]=pathsName[i].substring(idini+1,idfin);
	                    	//System.out.println(pathsName[i]+" videop id ->  " + pathsNumV[i] );
	 
	 
	                        newPathVideo[i] = baseDirw + "\\" + pathsACA[i]+ "\\" + pathsNumV[i]+ "\\" +pathsName[i]; 

	                        	try {
	                        		pathDest = Paths.get(newPathVideo[i]);
	                        		if (pathDest.toString().compareTo(listFile[i].toPath().toString()) != 0 ){
										Files.move(listFile[i].toPath(),pathDest,StandardCopyOption.REPLACE_EXISTING);
		                        		System.out.println("entra if -> \n"+ listFile[i].toPath()+"\n"+pathDest);
										cntmove++;
										swingControl.commentTextArea.append("MOVED: " + pathDest +"\n");
										Log.escriureMissatge("MOVED: " + pathDest );
										
										
										//System.out.println(cntmove);
										
	                        		}
	                        		}
									
								 catch (Exception e) {

									System.out.printf("### ERROR When move %s\t->\t%s\n",paths[i],newPathVideo[i]);
									swingControl.commentTextArea.append("# ERROR: " +paths[i] +"\n");
									Log.escriureMissatge("### ERROR: " +paths[i] );
									// e.printStackTrace();
									
								 }
	                        }
                    	
                    	catch (Exception e){
                    		e.printStackTrace();
                    }
                    
                  }
               }
           
            }
        }
	}



	  
}  

