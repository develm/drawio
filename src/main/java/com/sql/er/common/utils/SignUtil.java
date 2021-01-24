package com.sql.er.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * 验签工具包
 * 
 * @author wk
 * 
 */
public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    public static String getMD5Code(String params) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bByte = md.digest(params.getBytes("GBK"));
            StringBuffer sBuffer = new StringBuffer();
            for (int i = 0; i < bByte.length; i++) {
                sBuffer.append(byteToArrayString(bByte[i]));
            }
            return sBuffer.toString();
        } catch (Exception e) {
            logger.error("获取参数的MD5编码异常");
            e.printStackTrace();
        }

        return null;
    }

    public static boolean checkSign(String sign, String timestamp, String body, String scecret) {
        String params = body + "&" + scecret + "&" + timestamp;
        logger.debug("签名参数:{}", params);
        String nsign = getMD5Code(params);
        logger.debug("coming sign:{}", sign);
        logger.debug("our sign:{}", nsign);
//        return true;
        return sign.equals(nsign);
    }
    
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;

        return strDigits[iD1] + strDigits[iD2];
    }

    public static String ecodeByMD5(String originstr) {

        String result = null;

        char hexDigits[] = {// 用来将字节转换成 16 进制表示的字符

        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        if (originstr != null) {

            try {

                // 返回实现指定摘要算法的 MessageDigest 对象

                MessageDigest md = MessageDigest.getInstance("MD5");

                // 使用utf-8编码将originstr字符串编码并保存到source字节数组

                byte[] source = originstr.getBytes("utf-8");

                // 使用指定的 byte 数组更新摘要

                md.update(source);

                // 通过执行诸如填充之类的最终操作完成哈希计算，结果是一个128位的长整数

                byte[] tmp = md.digest();

                // 用16进制数表示需要32位

                char[] str = new char[32];

                for (int i = 0, j = 0; i < 16; i++) {

                    // j表示转换结果中对应的字符位置

                    // 从第一个字节开始，对 MD5 的每一个字节

                    // 转换成 16 进制字符

                    byte b = tmp[i];

                    // 取字节中高 4 位的数字转换

                    // 无符号右移运算符>>> ，它总是在左边补0

                    // 0x代表它后面的是十六进制的数字. f转换成十进制就是15

                    str[j++] = hexDigits[b >>> 4 & 0xf];

                    // 取字节中低 4 位的数字转换

                    str[j++] = hexDigits[b & 0xf];

                }

                result = new String(str);// 结果转换成字符串用于返回

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     * 使用私钥进行签名的方法
     * 
     * @param text
     *            待签名的数据
     * @param privateKeyData
     *            私钥数据
     * @param algorithm
     *            签名算法,SHA256withRSA,    
     * @return 签名后的数据
     * @throws GeneralSecurityException
     */
    public static byte[] signRSA(final byte[] text, final byte[] privateKeyData, final String algorithm)
                                                                                                     throws GeneralSecurityException {
        final PrivateKey privateKey = getPrivateKeyRSA(privateKeyData, algorithm);
        final Signature signatureChecker = Signature.getInstance(algorithm);
        signatureChecker.initSign(privateKey);
        signatureChecker.update(text);
        return signatureChecker.sign();
    }

    /**
     * 使用公钥进行验签的方法
     * 
     * @param text
     *            原始数据数据
     * @param signedText
     *            签名过的数据
     *            公钥数据
     * @param algorithm
     *            签名算法,目前支持 SHA256withRSA
     * @return 如果验签成功,返回true,验签失败,返回false
     * @throws GeneralSecurityException
     * @throws IOException 
     */
    public static boolean verifyRSA(final byte[] text, final byte[] signedText, File cert,
                                 final String algorithm) throws GeneralSecurityException,
                                                        IOException {
        final PublicKey publicKey = getPublicKeyRSA(cert);
        final Signature signatureChecker = Signature.getInstance(algorithm);
        signatureChecker.initVerify(publicKey);
        signatureChecker.update(text);
        return signatureChecker.verify(signedText);
    }

    /**
     * 得到私钥
     * 
     * @param keyData
     *            密钥数据
     * @throws GeneralSecurityException
     */
    private static PrivateKey getPrivateKeyRSA(final byte[] keyData, final String algorithm)
                                                                                         throws GeneralSecurityException {
        final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyData);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 得到公钥
     * 
     * @param cert
     *            证书文件
     * @throws GeneralSecurityException
     * @throws IOException 
     */
    private static PublicKey getPublicKeyRSA(final File cert) throws GeneralSecurityException,
                                                          IOException {
        final InputStream certIn = new FileInputStream(cert);
        final CertificateFactory cf1 = CertificateFactory.getInstance("X.509");
        final X509Certificate certificate = (X509Certificate) cf1.generateCertificate(certIn);
        return certificate.getPublicKey();
    }
}
