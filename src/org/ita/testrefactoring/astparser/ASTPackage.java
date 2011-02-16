package org.ita.testrefactoring.astparser;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.ita.testrefactoring.metacode.Package;

public class ASTPackage implements Package, ASTWrapper<PackageDeclaration> {

	private Map<String, ASTSourceFile> sourceFileList = new HashMap<String, ASTSourceFile>();
	private String name;
	private ASTEnvironment parent;
	private PackageDeclaration astObject;

	@Override
	public Map<String, ASTSourceFile> getSourceFileList() {
		return sourceFileList;
	}

	@Override
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	@Override
	public ASTEnvironment getEnvironment() {
		return parent;
	}

	protected void setEnvironment(ASTEnvironment parent) {
		this.parent = parent;
	}

	protected ASTSourceFile createSourceFile(String name) {
		ASTSourceFile sourceFile = new ASTSourceFile();
		sourceFile.setPackage(this);
		
		getSourceFileList().put(name, sourceFile);

		return sourceFile;
	}

	@Override
	/**
	 * Não deveria ser public, mas a interface e igiu
	 */
	public void setASTObject(PackageDeclaration astObject) {
		this.astObject = astObject;
	}

	@Override
	public PackageDeclaration getASTObject() {
		return astObject;
	}
	
	@Override
	public String toString() {
		return name;
	}

}