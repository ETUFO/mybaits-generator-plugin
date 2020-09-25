import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;



@Mojo(name = "mybatisplus-generate-plugin", threadSafe = true)
public class MyMojo
        extends AbstractMojo {

    @Parameter(property="configFilePath")
    private String configFilePath;

    @Override
    public void execute()  throws MojoExecutionException {
        if(StringUtils.isEmpty(configFilePath)){
            throw new MojoExecutionException("找不到配置文件路径");
        }
        try {
            CodeGenerator.generate(configFilePath);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
