
package com.digitalpetri.opcua.stack.core.types.structured;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.serialization.UaStructure;
import com.digitalpetri.opcua.stack.core.types.builtin.ExtensionObject;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;
import com.digitalpetri.opcua.stack.core.types.builtin.StatusCode;

public class MonitoredItemModifyResult implements UaStructure {

	public static final NodeId TypeId = Identifiers.MonitoredItemModifyResult;
	public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemModifyResult_Encoding_DefaultBinary;
	public static final NodeId XmlEncodingId = Identifiers.MonitoredItemModifyResult_Encoding_DefaultXml;

	protected final StatusCode _statusCode;
	protected final Double _revisedSamplingInterval;
	protected final Long _revisedQueueSize;
	protected final ExtensionObject _filterResult;

	public MonitoredItemModifyResult(StatusCode _statusCode, Double _revisedSamplingInterval, Long _revisedQueueSize, ExtensionObject _filterResult) {

		this._statusCode = _statusCode;
		this._revisedSamplingInterval = _revisedSamplingInterval;
		this._revisedQueueSize = _revisedQueueSize;
		this._filterResult = _filterResult;
	}

	public StatusCode getStatusCode() { return _statusCode; }
	public Double getRevisedSamplingInterval() { return _revisedSamplingInterval; }
	public Long getRevisedQueueSize() { return _revisedQueueSize; }
	public ExtensionObject getFilterResult() { return _filterResult; }

	@Override
	public NodeId getTypeId() { return TypeId; }

	@Override
	public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

	@Override
	public NodeId getXmlEncodingId() { return XmlEncodingId; }


	public static void encode(MonitoredItemModifyResult monitoredItemModifyResult, UaEncoder encoder) {
		encoder.encodeStatusCode("StatusCode", monitoredItemModifyResult._statusCode);
		encoder.encodeDouble("RevisedSamplingInterval", monitoredItemModifyResult._revisedSamplingInterval);
		encoder.encodeUInt32("RevisedQueueSize", monitoredItemModifyResult._revisedQueueSize);
		encoder.encodeExtensionObject("FilterResult", monitoredItemModifyResult._filterResult);
	}

	public static MonitoredItemModifyResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        Double _revisedSamplingInterval = decoder.decodeDouble("RevisedSamplingInterval");
        Long _revisedQueueSize = decoder.decodeUInt32("RevisedQueueSize");
        ExtensionObject _filterResult = decoder.decodeExtensionObject("FilterResult");

		return new MonitoredItemModifyResult(_statusCode, _revisedSamplingInterval, _revisedQueueSize, _filterResult);
	}

	static {
		DelegateRegistry.registerEncoder(MonitoredItemModifyResult::encode, MonitoredItemModifyResult.class, BinaryEncodingId, XmlEncodingId);
		DelegateRegistry.registerDecoder(MonitoredItemModifyResult::decode, MonitoredItemModifyResult.class, BinaryEncodingId, XmlEncodingId);
	}

}
