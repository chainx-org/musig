package com.chainx.musig;

import android.text.TextUtils;

import com.sun.jna.Native;

public class Mast {
    public String generateThresholdPubkey(String[] pubkeys, byte threshold) {
        return clib.generate_threshold_pubkey(TextUtils.join("", pubkeys).toString(), threshold);
    }

    public String generateControlBlock(String[] pubkeys, byte threshold, String sigAggPubkey) {
        return clib.generate_control_block(TextUtils.join("", pubkeys).toString(), threshold, sigAggPubkey);
    }

    final CLibrary clib = (CLibrary) Native.load(
            "musig_dll",
            CLibrary.class);
}
