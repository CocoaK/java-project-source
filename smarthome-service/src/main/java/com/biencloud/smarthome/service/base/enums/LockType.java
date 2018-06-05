package com.biencloud.smarthome.service.base.enums;

/**
 * 锁类型枚举类。
 * @author kouy
 * @since 1.0 2012-7-13
 */
public enum LockType {

	/** 锁定当前事务要读的数据，直到事务结束才释放锁，可防止当前事务连续读时数据不会被修改 */
	PESSIMISTIC_READ,
	/** 锁定当前事务更新的数据，直到事务结束才释放锁，可防止并发更新数据 */
	PESSIMISTIC_WRITE;
}
