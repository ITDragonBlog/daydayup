package com.itdragon.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 自定义拦截规则
 * @author itdragon
 *
 */
public class ITDragonCustomTypeFilter implements TypeFilter{

	/**
	 * @param MetadataReader 读取当前正在扫描的类信息
	 * @param MetadataReaderFactory 获取到其他任何类信息的
	 */
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		// 获取当前类的注解信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		// 获取当前类的资源信息
		Resource resource = metadataReader.getResource();
		// 获取当前正在扫描的类信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		// 获取当前类的类名
		String className = classMetadata.getClassName();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^" + className);
		if (className.contains("Controller")) {
			return true;
		}
		return false;
	}

}
