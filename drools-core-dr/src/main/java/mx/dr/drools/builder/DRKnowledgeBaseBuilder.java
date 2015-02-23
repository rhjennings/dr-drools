package mx.dr.drools.builder;

import java.io.IOException;
import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.builder.impl.KnowledgeBuilderImpl;
import org.drools.compiler.Dialect;
import org.drools.io.ResourceFactory;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public final class DRKnowledgeBaseBuilder {
	private Context context;
	private String property;
	private String knowledgePath;
	private ResourceType resourceType;
	
	private DRKnowledgeBaseBuilder() {}
	
	public static DRKnowledgeBaseBuilder newBulder() {
		return new DRKnowledgeBaseBuilder();
	}
	
	public DRKnowledgeBaseBuilder withContext(Context context) {
		this.context = context;
		return this;
	}
	
	public DRKnowledgeBaseBuilder withProperty(String property) {
		this.property = property;
		return this;
	}
	
	public DRKnowledgeBaseBuilder withKnowledgePath(String knowledgePath) {
		this.knowledgePath = knowledgePath;
		return this;
	}
	
	public DRKnowledgeBaseBuilder withResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
		return this;
	}
	
	public KnowledgeBase build() {
		Properties props = new Properties();
		try {
			props.load(Dialect.class.getResourceAsStream(property));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		for (String fname: context.getFilesDir().list()){
			if (fname.endsWith("dex")||fname.endsWith("apk")){
				context.deleteFile(fname);
			}
		}
		KnowledgeBuilderConfiguration kbuilderConf = 
				KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(props, Dialect.class.getClassLoader() );
		

		System.setProperty("java.version", "1.5");
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(kbuilderConf);
		((KnowledgeBuilderImpl)kbuilder).setPackage(context.getPackageName());
		
		kbuilder.add(ResourceFactory.newClassPathResource(knowledgePath), resourceType);
		if( kbuilder.hasErrors() ) {
			if (context instanceof Activity) {
				((Activity)context).runOnUiThread(new Runnable() {
					  public void run() {
						  Toast.makeText(context,
									kbuilder.getErrors().toString(), Toast.LENGTH_SHORT).show();
					  }
					});	
			}else{
				System.err.println(kbuilder.getErrors().toString());
			}
			
		    return null;
		}
		KnowledgeBaseConfiguration kbaseConf =
                KnowledgeBaseFactory.newKnowledgeBaseConfiguration(null, ClassLoader.getSystemClassLoader());
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConf);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
	}
}
