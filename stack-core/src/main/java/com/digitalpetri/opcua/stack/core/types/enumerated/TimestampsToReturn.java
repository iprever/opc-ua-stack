

package com.digitalpetri.opcua.stack.core.types.enumerated;

import com.digitalpetri.opcua.stack.core.Identifiers;
import com.digitalpetri.opcua.stack.core.serialization.DelegateRegistry;
import com.digitalpetri.opcua.stack.core.serialization.UaDecoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEncoder;
import com.digitalpetri.opcua.stack.core.serialization.UaEnumeration;
import com.digitalpetri.opcua.stack.core.types.builtin.NodeId;

public enum TimestampsToReturn implements UaEnumeration {

    Source(0),
    Server(1),
    Both(2),
    Neither(3); 

	public static final NodeId TypeId = Identifiers.TimestampsToReturn;

    private final int value;

    private TimestampsToReturn(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

	public static void encode(TimestampsToReturn timestampsToReturn, UaEncoder encoder) {
		encoder.encodeInt32(null, timestampsToReturn.ordinal());
	}

	public static TimestampsToReturn decode(UaDecoder decoder) {
		int value = decoder.decodeInt32(null);
		return TimestampsToReturn.values()[value];
	}

	static {
		DelegateRegistry.registerEncoder(TimestampsToReturn::encode, TimestampsToReturn.class);
		DelegateRegistry.registerDecoder(TimestampsToReturn::decode, TimestampsToReturn.class);
	}

}
