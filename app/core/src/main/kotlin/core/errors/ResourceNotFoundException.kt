package core.errors

public open class ResourceNotFoundException(
  title: String,
  detail: String,
  specifics: List<String>,
) : DomainException(title = title, detail = detail, specifics = specifics) {

  public constructor(title: String, detail: String) : this(title, detail, listOf())

  public constructor(detail: String) : this("Resource Not Found", detail, listOf())
}
