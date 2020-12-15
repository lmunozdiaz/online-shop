package cs.roosevelt.onlineshop.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This Product class is used to represent a Product entity from the database
 * table 'ORDERS'.
 */
@Entity
@Table(name = "ORDERS")
@Data
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ORDER_ID")
	private String id;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "ORDER_AMOUNT")
	@NotNull
	private BigDecimal amount;
	
	@Column(name = "CREATE_TIME")
	@CreationTimestamp
	private Date createTime;

	@Column(name = "UPDATE_TIME")
	@UpdateTimestamp
	private Date updateTime;

}
