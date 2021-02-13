package de.lexasoft.common.model;

/**
 * Base class for all Value Objects regarding the DDD patterns.
 * <p>
 * The value is immutable, which means the value object can just represent one
 * value at once.
 * 
 * @author nierax
 *
 * @param <T>
 */
@ValueObject
public abstract class SimpleType<T> {

	private final T value;

	/**
	 * Should be constructed in extension only.
	 * 
	 * @param value Value of the object, immutable.
	 */
	protected SimpleType(T value) {
		this.value = value;
	}

	/**
	 * Access to the scalar object.
	 * 
	 * @return
	 */
	public T value() {
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SimpleType)) {
			return false;
		}
		if (super.equals(obj)) {
			return true;
		}
		@SuppressWarnings("unchecked")
		SimpleType<T> other = (SimpleType<T>) obj;
		if ((value == other.value)) {
			return true;
		}
		if ((value == null) && (other.value != null)) {
			return false;
		}
		return value.equals(other.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
