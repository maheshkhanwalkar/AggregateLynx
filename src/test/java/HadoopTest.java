import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HadoopTest
{
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();

        conf.addResource(new Path("/opt/hadoop-2.6.0/etc/hadoop/core-site.xml"));
        conf.addResource(new Path("/opt/hadoop-2.6.0/etc/hadoop/hdfs-site.xml"));

        FileSystem fs = FileSystem.get(conf);
        Path file = new Path("hdfs://localhost:9000/sample.txt");


        if (fs.exists(file))
            fs.delete(file, true);

        FSDataOutputStream fos = fs.create(file);

        fos.writeBytes("Hello world!!!\n");
        fos.flush();

        fos.close();


        FSDataInputStream fis = fs.open(file);
        String tmp;

        while ((tmp = fis.readLine()) != null)
        {
            System.out.println(tmp);
        }

        fis.close();
        fs.close();
    }
}
