
package com.digitalpetri.opcua.stack.core.types.structured;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.types.builtin.LocalizedText;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;

public class ReferenceTypeAttributes extends NodeAttributes {

	public static final NodeId TypeId = Identifiers.ReferenceTypeAttributes;
	public static final NodeId BinaryEncodingId = Identifiers.ReferenceTypeAttributes_Encoding_DefaultBinary;
	public static final NodeId XmlEncodingId = Identifiers.ReferenceTypeAttributes_Encoding_DefaultXml;

	protected final Boolean _isAbstract;
	protected final Boolean _symmetric;
	protected final LocalizedText _inverseName;

	public ReferenceTypeAttributes(Long _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, Long _writeMask, Long _userWriteMask, Boolean _isAbstract, Boolean _symmetric, LocalizedText _inverseName) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);

		this._isAbstract = _isAbstract;
		this._symmetric = _symmetric;
		this._inverseName = _inverseName;
	}

	public Boolean getIsAbstract() { return _isAbstract; }
	public Boolean getSymmetric() { return _symmetric; }
	public LocalizedText getInverseName() { return _inverseName; }

	@Override
	public NodeId getTypeId() { return TypeId; }

	@Override
	public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

	@Override
	public NodeId getXmlEncodingId() { return XmlEncodingId; }


	public static void encode(ReferenceTypeAttributes referenceTypeAttributes, UaEncoder encoder) {
		encoder.encodeUInt32("SpecifiedAttributes", referenceTypeAttributes._specifiedAttributes);
		encoder.encodeLocalizedText("DisplayName", referenceTypeAttributes._displayName);
		encoder.encodeLocalizedText("Description", referenceTypeAttributes._description);
		encoder.encodeUInt32("WriteMask", referenceTypeAttributes._writeMask);
		encoder.encodeUInt32("UserWriteMask", referenceTypeAttributes._userWriteMask);
		encoder.encodeBoolean("IsAbstract", referenceTypeAttributes._isAbstract);
		encoder.encodeBoolean("Symmetric", referenceTypeAttributes._symmetric);
		encoder.encodeLocalizedText("InverseName", referenceTypeAttributes._inverseName);
	}

	public static ReferenceTypeAttributes decode(UaDecoder decoder) {
        Long _specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        Long _writeMask = decoder.decodeUInt32("WriteMask");
        Long _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        Boolean _isAbstract = decoder.decodeBoolean("IsAbstract");
        Boolean _symmetric = decoder.decodeBoolean("Symmetric");
        LocalizedText _inverseName = decoder.decodeLocalizedText("InverseName");

		return new ReferenceTypeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _isAbstract, _symmetric, _inverseName);
	}

	static {
		DelegateRegistry.registerEncoder(ReferenceTypeAttributes::encode, ReferenceTypeAttributes.class, BinaryEncodingId, XmlEncodingId);
		DelegateRegistry.registerDecoder(ReferenceTypeAttributes::decode, ReferenceTypeAttributes.class, BinaryEncodingId, XmlEncodingId);
	}

}
