
package com.digitalpetri.opcua.stack.core.types.structured;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.serialization.UaStructure;
import com.digitalpetri.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;

public class NodeTypeDescription implements UaStructure {

	public static final NodeId TypeId = Identifiers.NodeTypeDescription;
	public static final NodeId BinaryEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultBinary;
	public static final NodeId XmlEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultXml;

	protected final ExpandedNodeId _typeDefinitionNode;
	protected final Boolean _includeSubTypes;
	protected final QueryDataDescription[] _dataToReturn;

	public NodeTypeDescription(ExpandedNodeId _typeDefinitionNode, Boolean _includeSubTypes, QueryDataDescription[] _dataToReturn) {

		this._typeDefinitionNode = _typeDefinitionNode;
		this._includeSubTypes = _includeSubTypes;
		this._dataToReturn = _dataToReturn;
	}

	public ExpandedNodeId getTypeDefinitionNode() { return _typeDefinitionNode; }
	public Boolean getIncludeSubTypes() { return _includeSubTypes; }
	public QueryDataDescription[] getDataToReturn() { return _dataToReturn; }

	@Override
	public NodeId getTypeId() { return TypeId; }

	@Override
	public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

	@Override
	public NodeId getXmlEncodingId() { return XmlEncodingId; }


	public static void encode(NodeTypeDescription nodeTypeDescription, UaEncoder encoder) {
		encoder.encodeExpandedNodeId("TypeDefinitionNode", nodeTypeDescription._typeDefinitionNode);
		encoder.encodeBoolean("IncludeSubTypes", nodeTypeDescription._includeSubTypes);
        encoder.encodeArray("DataToReturn", nodeTypeDescription._dataToReturn, encoder::encodeSerializable);
	}

	public static NodeTypeDescription decode(UaDecoder decoder) {
        ExpandedNodeId _typeDefinitionNode = decoder.decodeExpandedNodeId("TypeDefinitionNode");
        Boolean _includeSubTypes = decoder.decodeBoolean("IncludeSubTypes");
        QueryDataDescription[] _dataToReturn = decoder.decodeArray("DataToReturn", decoder::decodeSerializable, QueryDataDescription.class);

		return new NodeTypeDescription(_typeDefinitionNode, _includeSubTypes, _dataToReturn);
	}

	static {
		DelegateRegistry.registerEncoder(NodeTypeDescription::encode, NodeTypeDescription.class, BinaryEncodingId, XmlEncodingId);
		DelegateRegistry.registerDecoder(NodeTypeDescription::decode, NodeTypeDescription.class, BinaryEncodingId, XmlEncodingId);
	}

}
