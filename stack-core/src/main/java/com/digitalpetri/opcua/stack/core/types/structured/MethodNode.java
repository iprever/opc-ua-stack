
package com.digitalpetri.opcua.stack.core.types.structured;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.types.builtin.LocalizedText;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;
import com.digitalpetri.opcua.stack.core.types.builtin.QualifiedName;
import com.digitalpetri.opcua.stack.core.types.enumerated.NodeClass;

public class MethodNode extends InstanceNode {

	public static final NodeId TypeId = Identifiers.MethodNode;
	public static final NodeId BinaryEncodingId = Identifiers.MethodNode_Encoding_DefaultBinary;
	public static final NodeId XmlEncodingId = Identifiers.MethodNode_Encoding_DefaultXml;

	protected final Boolean _executable;
	protected final Boolean _userExecutable;

	public MethodNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, Long _writeMask, Long _userWriteMask, ReferenceNode[] _references, Boolean _executable, Boolean _userExecutable) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);

		this._executable = _executable;
		this._userExecutable = _userExecutable;
	}

	public Boolean getExecutable() { return _executable; }
	public Boolean getUserExecutable() { return _userExecutable; }

	@Override
	public NodeId getTypeId() { return TypeId; }

	@Override
	public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

	@Override
	public NodeId getXmlEncodingId() { return XmlEncodingId; }


	public static void encode(MethodNode methodNode, UaEncoder encoder) {
		encoder.encodeNodeId("NodeId", methodNode._nodeId);
        encoder.encodeSerializable("NodeClass", methodNode._nodeClass);
		encoder.encodeQualifiedName("BrowseName", methodNode._browseName);
		encoder.encodeLocalizedText("DisplayName", methodNode._displayName);
		encoder.encodeLocalizedText("Description", methodNode._description);
		encoder.encodeUInt32("WriteMask", methodNode._writeMask);
		encoder.encodeUInt32("UserWriteMask", methodNode._userWriteMask);
        encoder.encodeArray("References", methodNode._references, encoder::encodeSerializable);
		encoder.encodeBoolean("Executable", methodNode._executable);
		encoder.encodeBoolean("UserExecutable", methodNode._userExecutable);
	}

	public static MethodNode decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeClass _nodeClass = decoder.decodeSerializable("NodeClass", NodeClass.class);
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        Long _writeMask = decoder.decodeUInt32("WriteMask");
        Long _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        ReferenceNode[] _references = decoder.decodeArray("References", decoder::decodeSerializable, ReferenceNode.class);
        Boolean _executable = decoder.decodeBoolean("Executable");
        Boolean _userExecutable = decoder.decodeBoolean("UserExecutable");

		return new MethodNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _executable, _userExecutable);
	}

	static {
		DelegateRegistry.registerEncoder(MethodNode::encode, MethodNode.class, BinaryEncodingId, XmlEncodingId);
		DelegateRegistry.registerDecoder(MethodNode::decode, MethodNode.class, BinaryEncodingId, XmlEncodingId);
	}

}
