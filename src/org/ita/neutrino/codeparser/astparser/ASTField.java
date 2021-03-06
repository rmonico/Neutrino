package org.ita.neutrino.codeparser.astparser;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.ita.neutrino.codeparser.AbstractCodeElement;
import org.ita.neutrino.codeparser.Expression;
import org.ita.neutrino.codeparser.Field;
import org.ita.neutrino.codeparser.NonAccessFieldModifier;
import org.ita.neutrino.codeparser.Type;
import org.ita.neutrino.codeparser.TypeListener;

public class ASTField extends AbstractCodeElement implements Field, ASTWrapper<FieldDeclaration> {

	private String name;
	private FieldTypeListener fieldTypeListener = new FieldTypeListener(); 
	private Type fieldType;
	private ParentTypeListener parentTypeListener = new ParentTypeListener();
	private NonAccessFieldModifier nonAccessModifier = new NonAccessFieldModifier();
	private Expression initialization;
	private ASTInnerElementAccessModifier accessModifier = new ASTInnerElementAccessModifier();
	private FieldDeclaration fieldDeclaration;
	
	
	private class FieldTypeListener implements TypeListener {
		@Override
		public void typePromoted(Type oldType, Type newType) {
			fieldType = newType;
		}
	}
	
	private class ParentTypeListener implements TypeListener {
		@Override
		public void typePromoted(Type oldType, Type newType) {
			parent = (ASTType) newType;
		}
	}
	
	@Override
	public ASTInnerElementAccessModifier getAccessModifier() {
		return accessModifier;
	}

	@Override
	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Type getParent() {
		return (Type) super.getParent();
	}

	void setParentType(Type parent) {
		if (this.parent != null) {
			getParent().removeListener(parentTypeListener);
		}
		
		this.parent = parent;
		
		if (this.parent != null) {
			getParent().addListener(parentTypeListener);
		}
	}

	@Override
	public Type getFieldType() {
		return fieldType;
	}
	
	void setFieldType(Type type) {
		if (fieldType != null) {
			fieldType.removeListener(fieldTypeListener);
		}
		
		fieldType = type;
		
		if (fieldType != null) {
			fieldType.addListener(fieldTypeListener);
		}
	}

	@Override
	public NonAccessFieldModifier getNonAccessModifier() {
		return nonAccessModifier;
	}

	@Override
	public Expression getInitialization() {
		return initialization;
	}
	
	public void setInitialization(Expression initialization) {
		this.initialization = initialization;
	}

	@Override
	public void setASTObject(FieldDeclaration astObject) {
		this.fieldDeclaration = astObject;
	}

	@Override
	public FieldDeclaration getASTObject() {
		return fieldDeclaration;
	}

}
