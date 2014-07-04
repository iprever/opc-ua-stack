
package com.digitalpetri.opcua.stack.core.types.structured;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.types.builtin.DataValue;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;
import com.digitalpetri.opcua.stack.core.types.enumerated.PerformUpdateType;

public class UpdateDataDetails extends HistoryUpdateDetails {

	public static final NodeId TypeId = Identifiers.UpdateDataDetails;
	public static final NodeId BinaryEncodingId = Identifiers.UpdateDataDetails_Encoding_DefaultBinary;
	public static final NodeId XmlEncodingId = Identifiers.UpdateDataDetails_Encoding_DefaultXml;

	protected final PerformUpdateType _performInsertReplace;
	protected final DataValue[] _updateValues;

	public UpdateDataDetails(NodeId _nodeId, PerformUpdateType _performInsertReplace, DataValue[] _updateValues) {
        super(_nodeId);

		this._performInsertReplace = _performInsertReplace;
		this._updateValues = _updateValues;
	}

	public PerformUpdateType getPerformInsertReplace() { return _performInsertReplace; }
	public DataValue[] getUpdateValues() { return _updateValues; }

	@Override
	public NodeId getTypeId() { return TypeId; }

	@Override
	public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

	@Override
	public NodeId getXmlEncodingId() { return XmlEncodingId; }


	public static void encode(UpdateDataDetails updateDataDetails, UaEncoder encoder) {
		encoder.encodeNodeId("NodeId", updateDataDetails._nodeId);
        encoder.encodeSerializable("PerformInsertReplace", updateDataDetails._performInsertReplace);
        encoder.encodeArray("UpdateValues", updateDataDetails._updateValues, encoder::encodeDataValue);
	}

	public static UpdateDataDetails decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        PerformUpdateType _performInsertReplace = decoder.decodeSerializable("PerformInsertReplace", PerformUpdateType.class);
        DataValue[] _updateValues = decoder.decodeArray("UpdateValues", decoder::decodeDataValue, DataValue.class);

		return new UpdateDataDetails(_nodeId, _performInsertReplace, _updateValues);
	}

	static {
		DelegateRegistry.registerEncoder(UpdateDataDetails::encode, UpdateDataDetails.class, BinaryEncodingId, XmlEncodingId);
		DelegateRegistry.registerDecoder(UpdateDataDetails::decode, UpdateDataDetails.class, BinaryEncodingId, XmlEncodingId);
	}

}
