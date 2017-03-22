package com.papabear.order.entity;


public class OrderConstant {
	public static enum OrderStatus {
		/** 待付款 */
		OBLIGATION {
			@Override
			public Byte getStatus() {
				return 10;
			}
		},

		/** 已付款（交易中） */
		PAID {
			@Override
			public Byte getStatus() {
				return 20;
			}
		},
		/** 私人订制确认 */
		CUSTOM_MADE_CONFIRM {
			@Override
			public Byte getStatus() {
				return 22;
			}
		},
		/** 面板打印中 */
		CUSTOM_PRINT{

			@Override
			public Byte getStatus() {
				// TODO Auto-generated method stub
				return 24;
			}
			
		},
		
		/** 私人订制中 */
		CUSTOM_MADING {
			@Override
			public Byte getStatus() {
				return 26;
			}
		},
		/** 私人订制已入库 */
		CUSTOM_MADE_STORAGE {
			@Override
			public Byte getStatus() {
				return 28;
			}
		},
		/** 配货（交易中） */
		PICKING {
			@Override
			public Byte getStatus() {
				return 30;
			}
		},
		/** 发货（交易中） */
		SIPPING {
			@Override
			public Byte getStatus() {
				return 40;
			}
		},
		/** 交易完成 */
		COMPLETED {
			@Override
			public Byte getStatus() {
				return 50;
			}
		},
		/** 订单已关闭 */
		CLOSED {
			@Override
			public Byte getStatus() {
				return 60;
			}
		},
		/** 售后服务中 */
		AFTERSALE {
			@Override
			public Byte getStatus() {
				return 70;
			}
		},
		/** 货到付款确认状态 */
		SOURCE {
			@Override
			public Byte getStatus() {
				return 80;
			}
		};
		public abstract Byte getStatus();
		public static OrderStatus get(Byte code) {

			for (OrderStatus ose : OrderStatus.values()) {
				if (ose.getStatus().intValue() == code.intValue()) {
					return ose;
				}
			}
			return null;
		}

	}

	public static enum OrderOperateTye {
		/** 系统 */
		SYSTEM {
			@Override
			public Byte getOperateType() {
				return 0;
			}
		},
		/** 用户 */
		USER {
			@Override
			public Byte getOperateType() {
				return 1;
			}
		},
		/** 管理员 */
		ADMINISTRATOR {
			@Override
			public Byte getOperateType() {
				return 2;
			}
		};
		public abstract Byte getOperateType();

	}

	public static enum ServiceRepairStatusEnum {
		/* 提交申请 */
		REPAIR_APPLY(10),

		/* 待签收 */
		REPAIR_WAIT_SIGN(20),

		/* 审核中 */
		REPAIR_CHECK(30),

		/* 申请换货已通过审核并已发货 */
		REPAIR_AGREE_REFUSE(40),

		/* 同意 */
		REPAIR_AGREE(41),

		/* 拒绝 */
		REPAIR_REFUSE(42),

		/* 维修中 */
		REPAIR_REPAIRING(50),

		/* 已退款 */
		REPAIR_SENDED(60),

		/* 已签收 */
		REPAIR_SIGNED(70);

		public byte getCode() {
			return this.code;
		}

		private byte code;

		private ServiceRepairStatusEnum(int code) {
			this.code = (byte) code;
		}

	}

	public static enum ServiceTypeEnum {
		/* 退货 */
		BACK(1),
		/* 换货 */
		EXCHANGE(2),
		/* 维修 */
		REPAIR(3);
		public byte getCode() {
			return this.code;
		}

		private byte code;

		private ServiceTypeEnum(int code) {
			this.code = (byte) code;
		}

	}

	public static enum ServiceBackStatusEnum {

		/* 提交申请 */
		BACK_APPLY(10),

		/* 待签收 */
		BACK_WAIT_SIGN(20),

		/* 审核中 */
		BACK_CHECK(30),

		/* 申请换货已通过审核并已发货 */
		BACK_AGREE_REFUSE(40),

		/* 同意 */
		BACK_AGREE(41),

		/* 拒绝 */
		BACK_REFUSE(42),

		// /* 等待退款 */ 取消
		// BACK_WAIT_REIMBURSE(50),

		/* 已退款 */
		BACK_REIMBURSED(50);

		public Byte getCode() {
			return this.code;
		}

		private Byte code;

		private ServiceBackStatusEnum(int code) {
			this.code = (byte) code;
		}
	}
}
