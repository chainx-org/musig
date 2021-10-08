package com.chainx.musig;

import android.text.TextUtils;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Musig {
    public String privkey;
    public String pubkey;
    public Pointer musig;

    public Musig(String priv) {
        privkey = priv;
        pubkey = clib.get_my_pubkey(priv);
        musig = clib.get_musig(priv);
    }

    public String getMyCommit() {
        return clib.get_my_commit(musig);
    }

    public String getMyPubkey() {
        return pubkey;
    }

    public String getMyReveal(String[] commits, String[] pubkeys) {
        musig = clib.reveal_stage(musig,
                TextUtils.join("", commits).toString(), TextUtils.join("", pubkeys).toString());
        return clib.get_my_reveal(musig);
    }

    public String getMyCosign(String[] reveals, String[] pubkeys) {
        musig = clib.cosign_stage(musig, TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString());
        return clib.get_my_cosign(musig);
    }

    public String getAggSignature(String[] reveals, String[] cosigns, String[] pubkeys) {
        return clib.get_signature(TextUtils.join("", reveals).toString(), TextUtils.join("", pubkeys).toString(), TextUtils.join("", cosigns).toString());
    }

    public String getAggPublicKey(String[] pubkeys) {
        return clib.get_agg_pubkey(TextUtils.join("", pubkeys).toString());
    }

    public String generateMulsigPubkey(String[] aggPubkeys, String innerPubkey) {
        return clib.generate_mulsig_pubkey(TextUtils.join("", aggPubkeys).toString(), innerPubkey);
    }

    public String generateControlBlock(String[] aggPubkeys, String innerPubkey, String sigAggPubkey) {
        return clib.generate_control_block(TextUtils.join("", aggPubkeys).toString(), innerPubkey, sigAggPubkey);
    }

    final CLibrary clib = (CLibrary) Native.load(
            "musig_dll",
            CLibrary.class);

    public interface CLibrary extends Library {
        public String get_my_pubkey(String priv);

        public Pointer get_musig(String priv);

        public String get_my_commit(Pointer musig);

        public Pointer reveal_stage(Pointer musig, String commits, String pubkeys);

        public String get_my_reveal(Pointer musig);

        public Pointer cosign_stage(Pointer musig, String reveals, String pubkeys);

        public String get_my_cosign(Pointer musig);

        public String get_signature(String reveals, String pubkeys, String cosigns);

        public String get_agg_pubkey(String pubkeys);

        public String generate_mulsig_pubkey(String aggPubkeys, String innerPubkey);

        public String generate_control_block(String aggPubkeys, String innerPubkey, String sigAggPubkey);
    }
}
