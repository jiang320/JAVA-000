package jike_training;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class myclassLoader  extends  ClassLoader{
//    private String path ="D:/java_dev/java_project/mypro2020/src/main/java/jike_training/" ;

    @Override
    protected Class<?> findClass (String name) throws ClassNotFoundException {

        String classFilename = name + ".xlass";
//        File classFile = new File(classFilename);
//        File classFile = new File(this.getClass()
//                .getResource("Hello.xlass").getPath());
       File classFile = new File("D:/java_dev/java_project/mypro2020/src/main/java/jike_training/Hello.xlass");
   //  String path = myclassLoader.class.getResource("Hello.xlass").getPath();
//        File classFile  = new File(path);
        int length =(int) classFile.length();
           byte[] bytes =new byte[length];

            try {
//                FileInputStream fis = new FileInputStream(file);
               //new FileInputStream(path).read(bytes);
               new FileInputStream(classFile).read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                return super.findClass(name);
            }
            for(int i=0;i<bytes.length;i++){
                bytes[i]= (byte) ((byte)255-bytes[i]);
            }

        return  defineClass(name,bytes,0,bytes.length);
    }

    public static void main(String[] args) throws Exception{
    //    myclassLoader myClassLoader = new myclassLoader();
//        Class clazz = myClassLoader.loadClass(args[0]);
        Class<?> myClass  = new myclassLoader().findClass("Hello");
        Object obj = myClass.newInstance();
        Method sayHello = myClass.getMethod("hello");
         sayHello.invoke(obj);
    }
}
