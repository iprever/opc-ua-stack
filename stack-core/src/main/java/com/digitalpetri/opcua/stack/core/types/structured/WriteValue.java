
package com.digitalpetri.opcua.stack.core.types.structured;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.serialization.UaStructure;
import com.digitalpetri.opcua.stack.core.types.builtin.DataValue;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;

public class WriteValue implements UaStructure {

	public static final NodeId TypeId = Identifiers.WriteValue;
	public static final NodeId BinaryEncodingId = Identifiers.WriteValue_Encoding_DefaultBinary;
	public static final NodeId XmlEncodingId = Identifiers.WriteValue_Encoding_DefaultXml;

	protected final NodeId _nodeId;
	protected final Long _attributeId;
	protected final String _indexRange;
	protected final DataValue _value;

	public WriteValue(NodeId _nodeId, Long _attributeId, String _indexRange, DataValue _value) {

		this._nodeId = _nodeId;
		this._attributeId = _attributeId;
		this._indexRange = _indexRange;
		this._value = _value;
	}

	public NodeId getNodeId() { return _nodeId; }
	public Long getAttributeId() { return _attributeId; }
	public String getIndexRange() { return _indexRange; }
	public DataValue getValue() { return _value; }

	@Override
	public NodeId getTypeId() { return TypeId; }

	@Override
	public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

	@Override
	public NodeId getXmlEncodingId() { return XmlEncodingId; }


	public static void encode(WriteValue writeValue, UaEncoder encoder) {
		encoder.encodeNodeId("NodeId", writeValue._nodeId);
		encoder.encodeUInt32("AttributeId", writeValue._attributeId);
		encoder.encodeString("IndexRange", writeValue._indexRange);
		encoder.encodeDataValue("Value", writeValue._value);
	}

	public static WriteValue decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        Long _attributeId = decoder.decodeUInt32("AttributeId");
        String _indexRange = decoder.decodeString("IndexRange");
        DataValue _value = decoder.decodeDataValue("Value");

		return new WriteValue(_nodeId, _attributeId, _indexRange, _value);
	}

	static {
		DelegateRegistry.registerEncoder(WriteValue::encode, WriteValue.class, BinaryEncodingId, XmlEncodingId);
		DelegateRegistry.registerDecoder(WriteValue::decode, WriteValue.class, BinaryEncodingId, XmlEncodingId);
	}

}
