package com.xjt.core.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.Map;

/**
 * �����ĸ�����
 * 
 * @author xjt
 * @since 2017-04-08
 */
public final class DataUtil {
	private DataUtil() {
	}

	/**
	 * ʮ�����ֽ�����תʮ�������ַ���
	 * 
	 * @param b
	 * @return
	 */
	public static final String byte2hex(byte[] b) { // һ���ֽ�����ת��16�����ַ���
		StringBuilder hs = new StringBuilder(b.length * 2);
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// ����ת��ʮ�����Ʊ�ʾ
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString(); // ת�ɴ�д
	}

	/**
	 * ʮ�������ַ���תʮ�����ֽ�����
	 * 
	 * @param b
	 * @return
	 */
	public static final byte[] hex2byte(String hs) {
		byte[] b = hs.getBytes();
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("���Ȳ���ż��");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// ��λһ�飬��ʾһ���ֽ�,��������ʾ��16�����ַ�������ԭ��һ��ʮ�����ֽ�
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * �����������ͨ����ĳ�����class�ļ������·������ȡ�ļ���Ŀ¼�ľ���·���� ͨ���ڳ����к��Ѷ�λĳ�����·�����ر�����B/SӦ���С�
	 * ͨ��������������ǿ��Ը������ǳ�����������ļ���λ������λĳ�����·����
	 * ���磺ĳ��txt�ļ�����ڳ����Test���ļ���·����../../resource/test.txt��
	 * ��ôʹ�ñ�����Path.getFullPathRelateClass("../../resource/test.txt",Test.class)
	 * �õ��Ľ����txt�ļ�����ϵͳ�еľ���·����
	 * 
	 * @param relatedPath ���·��
	 * @param cls ������λ����
	 * @return ���·������Ӧ�ľ���·��
	 * @throws IOException ��Ϊ����������ѯ�ļ�ϵͳ�����Կ����׳�IO�쳣
	 */
	public static final String getFullPathRelateClass(String relatedPath, Class<?> cls) {
		String path = null;
		if (relatedPath == null) {
			throw new NullPointerException();
		}
		String clsPath = getPathFromClass(cls);
		File clsFile = new File(clsPath);
		String tempPath = clsFile.getParent() + File.separator + relatedPath;
		File file = new File(tempPath);
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * ��ȡclass�ļ����ھ���·��
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 */
	public static final String getPathFromClass(Class<?> cls) {
		String path = null;
		if (cls == null) {
			throw new NullPointerException();
		}
		URL url = getClassLocationURL(cls);
		if (url != null) {
			path = url.getPath();
			if ("jar".equalsIgnoreCase(url.getProtocol())) {
				try {
					path = new URL(path).getPath();
				} catch (MalformedURLException e) {
				}
				int location = path.indexOf("!/");
				if (location != -1) {
					path = path.substring(0, location);
				}
			}
			File file = new File(path);
			try {
				path = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	/**
	 * �ж϶����Ƿ�Empty(null��Ԫ��Ϊ0)<br>
	 * ʵ���ڶ����¶������ж�:String Collection�������� Map��������
	 * 
	 * @param pObj ��������
	 * @return boolean ���صĲ���ֵ
	 */
	public static final boolean isEmpty(Object pObj) {
		if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).trim().length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection<?>) {
			if (((Collection<?>) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map<?, ?>) {
			if (((Map<?, ?>) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �ж϶����Ƿ�ΪNotEmpty(!null��Ԫ��>0)<br>
	 * ʵ���ڶ����¶������ж�:String Collection�������� Map��������
	 * 
	 * @param pObj ��������
	 * @return boolean ���صĲ���ֵ
	 */
	public static final boolean isNotEmpty(Object pObj) {
		if (pObj == null)
			return false;
		if (pObj == "")
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).trim().length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection<?>) {
			if (((Collection<?>) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map<?, ?>) {
			if (((Map<?, ?>) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * JS�������\n�����⴦��
	 * 
	 * @param pStr
	 * @return
	 */
	public static final String replace4JsOutput(String pStr) {
		pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
		pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		pStr = pStr.replace(" ", "&nbsp;");
		return pStr;
	}

	/**
	 * �ֱ�ȥ�ո�
	 * 
	 * @param paramArray
	 * @return
	 */
	public static final String[] trim(String[] paramArray) {
		if (ArrayUtils.isEmpty(paramArray)) {
			return paramArray;
		}
		String[] resultArray = new String[paramArray.length];
		for (int i = 0; i < paramArray.length; i++) {
			String param = paramArray[i];
			resultArray[i] = StringUtils.trim(param);
		}
		return resultArray;
	}

	/**
	 * ��ȡ���class�ļ�λ�õ�URL
	 * 
	 * @param cls
	 * @return
	 */
	private static URL getClassLocationURL(final Class<?> cls) {
		if (cls == null)
			throw new IllegalArgumentException("null input: cls");
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			if (cs != null)
				result = cs.getLocation();
			if (result != null) {
				if ("file".equals(result.getProtocol())) {
					try {
						if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip"))
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						else if (new File(result.getFile()).isDirectory())
							result = new URL(result, clsAsResource);
					} catch (MalformedURLException ignore) {
					}
				}
			}
		}
		if (result == null) {
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource)
					: ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}

	/** ��ʼ������Ĭ��ֵ */
	public static final <K> K ifNull(K k, K defaultValue) {
		if (k == null) {
			return defaultValue;
		}
		return k;
	}
}