package core.errors

/**
 * This is a base class for core-level exceptions. Preferably all business-logic exceptions should
 * derive from this class, it allows adapter layer to do some additional handling. For example, Web layer
 * can map these exceptions to corresponding HTTP response.
 */
public open class DomainException(
  public val title: String,
  public val detail: String,
  public val specifics: List<String>,
) : RuntimeException(detail)
