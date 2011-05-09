package org.ita.neutrino.astparser;

import java.util.HashMap;

import org.ita.neutrino.codeparser.Type;

public class TypeCache extends HashMap<String, Type> {
	
	private ASTEnvironment environment;

	public TypeCache(ASTEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7973930399495455846L;

	@Override
	public Type get(Object key) {
		if (key == null) {
			key = "java.lang.Object";
		}
		
		Type cachedType = super.get(key);
		
		if (cachedType == null) {
			String fullQualifiedName = key.toString();
			
			String packageName = ASTEnvironment.extractPackageName(fullQualifiedName);
			String typeName = ASTEnvironment.extractTypeName(fullQualifiedName);

			ASTPackage pack = environment.getOrCreatePackage(packageName);
			
			Type newType = environment.createDummyType(typeName, pack);
			
			cachedType = super.get(newType.getQualifiedName());
		}
		
		return cachedType;
	}
}