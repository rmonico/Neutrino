package org.ita.neutrino.codeparser;

import java.util.Map;

public interface Type extends CodeElement {

	/**
	 * Source file parent.
	 * 
	 * @return
	 */
	SourceFile getParent();

	/**
	 * Package ao qual pertence o Type
	 * 
	 * @return
	 */
	Package getPackage();

	/**
	 * Nome do tipo.
	 * 
	 * @return
	 */
	String getName();

	// boolean isGeneric();

	/**
	 * 
	 * TODO Este método
	 * 
	 * Parâmetros genéricos. Só devem ser permitidos para classes e interfaces.
	 * No caso de enum's e annotations sempre deve devolver null.
	 * 
	 * @return
	 */
	// List<GenericParameter> getGenericParameters();

	/**
	 * Modificador de acesso
	 */
	TypeAccessModifier getAccessModifier();

	/**
	 * Lista de campos
	 * 
	 * @return
	 */
	Map<String, Field> getFieldList();

	/**
	 * Lista de construtores. A chave do Map é a string com os parâmetros
	 * recebidos pelo construtor. Por exemplo: (param1package.Param1Class,
	 * param2package.Param2Class)
	 * 
	 * @return
	 */
	Map<String, Constructor> getConstructorList();

	/**
	 * Lista de métodos. A chave do Map é o nome do método, por exemplo:
	 * doSomethingMethod
	 * 
	 * TODO: mudar a chave do Map para o nome do método seguido de seus
	 * parâmetros, por exemplo:
	 * 
	 * doSomethingMethod(param1package.Param1Class, param2package.Param2Class);
	 * 
	 * Pois da forma que está não é possível localizar os overrides do método.
	 * 
	 * @return
	 */
	Map<String, Method> getMethodList();

	/**
	 * Kind do tipo, indica se é uma classe, etc
	 * 
	 * @return
	 */
	TypeKind getKind();

	/**
	 * Devolve o nome qualificado do tipo.
	 * 
	 * @return
	 */
	String getQualifiedName();

	/**
	 * Promove o tipo, na maioria das implementações apenas vai notificar os
	 * listeners da mudança. É necessário que <code>newType</code> tenha o mesmo
	 * <code>getQualifieName()</code> que <code>this</code>.
	 */
	void promote(Type newType);

	/**
	 * Adiciona um listener de tipo, são usados para informar outras instâncias
	 * quando um tipo é promovido.
	 * 
	 * @param listener
	 */
	void addListener(TypeListener listener);

	/**
	 * Remove um listener de tipo.
	 * 
	 * @param listener
	 */
	void removeListener(TypeListener listener);

	Method getOrCreateMethod(String methodName);

	/**
	 * Devolve o construtor baseado na lista de parâmetros passada. Por exemplo:
	 * 
	 * (param1packagename.Param1Class, param2packagename.Param2Class)
	 * 
	 * @param constructorParams
	 * @return
	 */
	Constructor getOrCreateConstructor(String constructorParams);
}
