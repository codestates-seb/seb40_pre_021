const textCharacterCheck = (str) => {
	const text = str;
	if (text.length >= 20) return true;
	else return false;
}; // 텍스트를 받아 20자 이내로 작성하였는지의 여부를 boolean으로 반환합니다.
export default textCharacterCheck;
