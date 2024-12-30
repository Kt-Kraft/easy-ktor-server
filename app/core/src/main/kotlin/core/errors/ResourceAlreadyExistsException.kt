package core.errors

public class ResourceAlreadyExistsException(
  title: String,
  detail: String,
) : DomainException(title = title, detail = detail, specifics = listOf()) {

  public constructor(detail: String) : this("Resource Already Exists", detail)
}
