import styled from 'styled-components';
import mypageDataSort from '../../../pages/Mypage/utils/mypageDataSort';
import useSortButton from '../hooks/useSortButton';

const SortButtonGroup = ({ menus, data, callback, tag = false }) => {
	const [menu, handleMenuChange] = useSortButton(menus);

	return (
		<GroupContainer>
			{menu.map((item) => {
				const { id, name, clicked } = item;
				return (
					<Menu
						key={id}
						clicked={clicked}
						onClick={() => {
							handleMenuChange(id);
							callback(mypageDataSort(name, data, tag));
						}}>
						{name}
					</Menu>
				);
			})}
		</GroupContainer>
	);
};

export default SortButtonGroup;

const GroupContainer = styled.div`
	display: flex;
	flex-wrap: wrap;
	margin-bottom: 1px;
	margin-right: 1px;
	border-radius: 3px;
`;

const Menu = styled.a`
	padding: 0.6em;
	font-size: 11px;
	font-weight: 400;
	box-shadow: none;
	border: 1px solid #838c95;
	margin-right: -1px;
	border-radius: 0;
	background-color: ${(props) => (props.clicked ? '#e3e6e8' : 'white')};
	color: ${(props) => (props.clicked ? '#3b4045' : '#6a737c')};
	:hover {
		background-color: ${(props) => (props.clicked ? '#e3e6e8' : '#f8f9f9')};
		cursor: pointer;
	}
	&:first-child {
		border-top-left-radius: 3px;
		border-bottom-left-radius: 3px;
	}
	&:last-child {
		border-top-right-radius: 3px;
		border-bottom-right-radius: 3px;
	}
`;
