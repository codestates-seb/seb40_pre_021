import styled from 'styled-components';
import image from '../../../../assets/images/sprites.svg';

const TagsList = ({ lists, limit }) => {
	return (
		<Container>
			{lists.map((list, i) => {
				const { title, tagCount } = list;

				if (limit && i >= limit) {
					return;
				}
				return (
					<Box key={title} limit={limit}>
						<ContentBox>
							<LeftCotent>
								<TagView>
									<Tag>{title}</Tag>
									<Badge tagCount={tagCount} />
								</TagView>
							</LeftCotent>
							<RightContent>
								<RightView>
									<FlexBox>
										<ScoreView>{tagCount.toLocaleString('ko-KR')}</ScoreView>
										<Text>score</Text>
									</FlexBox>
									<FlexBox>
										<ScoreView>{tagCount.toLocaleString('ko-KR')}</ScoreView>
										<Text>posts</Text>
									</FlexBox>
								</RightView>
							</RightContent>
						</ContentBox>
					</Box>
				);
			})}
		</Container>
	);
};

export default TagsList;

const Container = styled.div`
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	width: 100%;
`;

const Box = styled.div`
	border-bottom: 1px solid #d7d9dc;
	padding: 12px;
	&:nth-child(props.limit) {
		border-bottom: none;
	}
	&:last-child {
		border-bottom: ${(props) => (props.limit ? 'none' : '')};
	}
`;

const ContentBox = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-wrap: wrap;
	margin: calc(var(12px) / 2 * -1);
	margin-top: 0;
	margin-bottom: 0;
`;

const FlexBox = styled.div`
	display: flex;
	align-items: center;
	margin: 0 8px;
`;

const LeftCotent = styled.div`
	display: flex;
	align-items: center;
	margin: calc(var(16px) / 2);
	white-space: nowrap;
`;

const RightContent = styled.div`
	display: flex;
	align-items: center;
	margin: calc(var(16px) / 2);
`;

const TagView = styled.div`
	display: inline-block;
	white-space: normal;
`;

const Tag = styled.span`
	display: inline-block;
	color: #39739d;
	background-color: #e1ecf4;
	font-size: 12px;
	padding: 0.4rem 0.5rem;
	margin: 2px 2px 2px 0;
	white-space: nowrap;
	text-align: center;
	border: 1px solid transparent;
	border-radius: 3px;
	font-weight: 400;
	cursor: pointer;
	:hover {
		color: #2c5877;
		background-color: #d1e3f0;
	}
`;

const Badge = styled.span`
	background-position: ${(props) => {
		const { tagCount } = props;
		if (tagCount >= 1000) {
			return '-102px -398px';
		} else if (tagCount <= 100) {
			return '';
		} else if (tagCount <= 300) {
			return '-62px -398px;';
		} else {
			return '-82px -398px';
		}
	}};
	margin-right: 3px;
	margin-left: 10px;
	width: 6px;
	height: 14px;
	background-image: url(${image});
	background-size: initial;
	background-repeat: no-repeat;
	overflow: hidden;
	display: inline-block;
	vertical-align: text-bottom;
`;

const RightView = styled.div`
	display: flex;
`;

const ScoreView = styled.div`
	font-size: 17px;
	margin-right: 4px;
	font-weight: 400;
`;

const Text = styled.div`
	text-transform: lowercase;
	color: #6a737c;
	font-weight: 400;
	font-size: 14px;
`;
