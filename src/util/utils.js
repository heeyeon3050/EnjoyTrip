export const formatDate = (localDateTime) => {
  console.log(localDateTime);
  const now = new Date();
  const [date, time] = localDateTime.split("T");

  const [year, month, day] = date.split("-");
  const [hour, minute, second] = time.split(":");

  let today = false;
  if (
    now.getFullYear() == year &&
    now.getMonth() + 1 == month &&
    now.getDate() == day
  ) {
    today = true;
  }
  return today ? `${hour}:${minute}` : `${month}/${day}`;
};
