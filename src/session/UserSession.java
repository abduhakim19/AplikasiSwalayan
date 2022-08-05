/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

/**
 *
 * @author hakim
 */
public class UserSession {
    private static int kd_karywan;
    private static String nama_karywan;
    public static int getKd_karyawan() {
        return kd_karywan;
    }

    public static void setKd_karyawan(int kd_karywan) {
        UserSession.kd_karywan = kd_karywan;
    }
    
    public static String getNama_karyawan() {
        return nama_karywan;
    }

    public static void setNama_karyawan(String nama_karywan) {
        UserSession.nama_karywan = nama_karywan;
    }
    
}
