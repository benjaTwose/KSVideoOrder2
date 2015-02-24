package VideoOrderPack;

/*
 * Order video.kids files that has been disaster uploaded in ftp server 
 * This will put all video file into his own video dir
 * 
 * 1rst parameter is the base dir to search
 * ex:  "D:\FTPVideos"
 */


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import VideoOrderPack.SwingControlDemo;

public class filesToOrder {

	
	public static String baseDirw ;
	public static int cntmove, cntfiles;
	public static SwingControlDemo swingControlDemo = new SwingControlDemo();
	
	
    public static void mine2() {
    	
    	
        // SwingControlDemo  swingControlDemo = new SwingControlDemo();      
       // swingControlDemo.showTextAreaDemo();
        swingControlDemo.commentTextArea.setText(" ### INI REORDER ###" +"\n");
    	
       	
   
      		 walkin(new File(baseDirw));

    	
    	System.out.println(" ### END REORDER ###" );
    	swingControlDemo.commentTextArea.append(" ### END REORDER ###" +"\n");
    }
 
	
    public static void main(String[] args) {
    	
    	
        // SwingControlDemo  swingControlDemo = new SwingControlDemo();      
        swingControlDemo.showTextAreaDemo();
        swingControlDemo.commentTextArea.setText(" ### INI REORDER ###" +"\n");
    	
    	try {
    	if ((baseDirw = args[0])!= null) {
      		 walkin(new File(baseDirw));
    	
    	}
    	System.out.println(" ### END REORDER ###" );
    	swingControlDemo.commentTextArea.append(" ### END REORDER ###" +"\n");
    	
    	}
    	
    	catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("### ERROR: " );
			swingControlDemo.commentTextArea.append("ERROR:" +e + "\n");
			e.printStackTrace();
			
		}
    }
 
	
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
            	swingControlDemo.lbcntvideos.setText("Progress: "+ cntmove+" / " + cntfiles);
                if (listFile[i].isDirectory()) {
                    walkin(listFile[i]);
                    //System.out.println("CHANGE DIR --- ");
                    
                } else {
                    paths[i]=listFile[i].getPath();
                    pathsDir[i]=listFile[i].getParent();
                    pathsName[i]=listFile[i].getName();
                    
                    //System.out.println("path: " + paths[i] + " ######### dir: "+ pathsDir[i] + " ######### filename: "+ pathsName[i] );
                    
                    
                    if ( listFile[i].getPath().indexOf("FTPVideos")==3){
                    	
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
	                        //kk if (newPathVideo[i] != paths[i])
	                        //kk {
	                        	try {
	                        		pathDest = Paths.get(newPathVideo[i]);
	                        		if (pathDest.toString().compareTo(listFile[i].toPath().toString()) != 0 ){
										//Files.move(listFile[i].toPath(),pathDest,StandardCopyOption.REPLACE_EXISTING);
		                        		System.out.println("entra if -> \n"+ listFile[i].toPath()+"\n"+pathDest);
										cntmove++;
										
										//System.out.println(cntmove);
										
	                        		}
	                        		}
									
								 catch (Exception e) {
									// TODO Auto-generated catch block
									System.out.printf("### ERROR When move %s\t->\t%s\n",paths[i],newPathVideo[i]);
									swingControlDemo.commentTextArea.append("ERROR:" +paths[i] +"\n");
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

